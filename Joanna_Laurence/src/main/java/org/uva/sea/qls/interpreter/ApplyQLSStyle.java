package org.uva.sea.qls.interpreter;

import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.ql.interpreter.dataObject.questionData.WidgetParameters;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.qls.parser.elements.Page;
import org.uva.sea.qls.parser.elements.Parameter;
import org.uva.sea.qls.parser.elements.Stylesheet;
import org.uva.sea.qls.parser.elements.specification.Question;
import org.uva.sea.qls.parser.elements.specification.Section;
import org.uva.sea.qls.parser.elements.style.Widget;
import org.uva.sea.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class ApplyQLSStyle extends BaseStyleASTVisitor<Void> {

    private InterpreterResult qlInputResult;

    private InterpreterResult outputResult;

    private EvaluateDefaultStyle.Fetcher defaultStyleEvaluator = new EvaluateDefaultStyle.Fetcher();

    private Page currentPage = null;

    private Stack<Section> currentSections = new Stack<>();


    /**
     * Hide constructor
     */
    private ApplyQLSStyle() {

    }


    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Linker {
        public InterpreterResult link(InterpreterResult interpreterResult, Stylesheet stylesheet) throws InterruptedException {
            ApplyQLSStyle interpreter = new ApplyQLSStyle();
            return interpreter.addStyle(interpreterResult, stylesheet);
        }
    }


    /**
     * Generate a new InterpreterResult with style
     * @param interpreterResult QL interpreterResult
     * @param stylesheet QLS AST
     * @throws InterruptedException
     */
    public InterpreterResult addStyle(InterpreterResult interpreterResult, Stylesheet stylesheet) throws InterruptedException {
        this.qlInputResult = interpreterResult;
        this.outputResult = new InterpreterResult(new ArrayList<>(), interpreterResult.getWarnings());
        //The visitor will fill the outputResult
        stylesheet.accept(this);
        return this.outputResult;
    }

    /**
     *
     * @param questionName Name for the question that has to be looked-up
     * @return The Question Data
     */
    private QuestionData getQuestionData(String questionName) {
        for (QuestionData questionData : this.qlInputResult.getQuestions()) {
            if (questionData.getQuestionName().equals(questionName)) {
                return questionData;
            }
        }

        return null;
    }

    @Override
    public Void visit(Page node) throws InterruptedException {
        this.currentPage = node;
        return super.visit(node);
    }

    @Override
    public Void visit(Section node) throws InterruptedException {
        this.currentSections.add(node);
        super.visit(node);
        this.currentSections.pop();
        return null;
    }


    @Override
    public Void visit(Question node) throws InterruptedException {

        QuestionData questionData = this.getQuestionData(node.getName());

        if(questionData != null) {
            WidgetType widgetType = node.getWidget() != null ? node.getWidget().getWidgetType() : getDefaultWidgetType(questionData.getNodeType());
            questionData.setWidgetType(widgetType);

            questionData.setStyle(getQuestionStyle(node, widgetType));
            this.outputResult.add(questionData);
        }

        return null;
    }

    /**
     * Get default style for a question
     * @param question Question node
     * @param widgetType What type of question
     * @return Style for the widget
     * @throws InterruptedException
     */
    private Style getQuestionStyle(Question question, WidgetType widgetType) throws InterruptedException {
        Style style = new Style();
        style.setPage(this.currentPage.getName());
        style.setSection(getCurrentSection());

        if(question.getWidget() != null)
            style.setWidget(getWidgetParameters(question.getWidget()));

        style.fillNullFields(getParentStyles(widgetType));
        return style;
    }

    /**
     * Lookup style in parent sections and pages
     * @param widgetType For what widget type the style has to be fetched
     * @return Cascading style
     * @throws InterruptedException
     */
    private Style getParentStyles(WidgetType widgetType) throws InterruptedException {
        Style style = new Style();

        ListIterator<Section> li = this.currentSections.listIterator(this.currentSections.size());
        while(li.hasPrevious()) {
            Style defaultStyle = this.defaultStyleEvaluator.findStyle(li.previous(), widgetType);
            style.fillNullFields(defaultStyle);
        }
        Style pageStyle = this.defaultStyleEvaluator.findStyle(this.currentPage, widgetType);
        style.fillNullFields(pageStyle);
        return style;
    }

    /**
     * Get list of the current point of sections
     * @return List of section
     */
    private List<String> getCurrentSection() {
        List<String> sections = new ArrayList<>();
        for(Section section : this.currentSections)
            sections.add(section.getName());
        return sections;
    }

    /**
     * Determine what NodeType type belongs to what WidgetType
     * @param nodeType
     * @return
     */
    private WidgetType getDefaultWidgetType(NodeType nodeType) {
        return WidgetType.valueOf(nodeType.toString());
    }

    /**
     *
     * @param node
     * @return
     */
    private WidgetParameters getWidgetParameters(Widget node) {
        List<String> parameters = new ArrayList<>();
        for(Parameter parameter : node.getParameters())
            parameters.add(parameter.getParameter());
        return new WidgetParameters(parameters);
    }
}
