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

    private InterpreterResult inputResult;

    private InterpreterResult outputResult;

    private EvaluateDefaultStyle.Fetcher defaultStyleEvaluator = new EvaluateDefaultStyle.Fetcher();

    private Page page = null;

    private Stack<Section> sections = new Stack<>();


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
     * @param interpreterResult
     * @param stylesheet
     * @throws InterruptedException
     */
    public InterpreterResult addStyle(InterpreterResult interpreterResult, Stylesheet stylesheet) throws InterruptedException {
        this.inputResult = interpreterResult;
        this.outputResult = new InterpreterResult(new ArrayList<>(), interpreterResult.getWarnings());
        stylesheet.accept(this);
        return this.outputResult;
    }

    /**
     *
     * @param questionName
     * @return
     */
    private QuestionData getQuestionData(String questionName) {
        for (QuestionData questionData : this.inputResult.getQuestions()) {
            if (questionData.getQuestionName().equals(questionName)) {
                return questionData;
            }
        }

        return null;
    }

    @Override
    public Void visit(Page node) throws InterruptedException {
        this.page = node;
        return super.visit(node);
    }

    @Override
    public Void visit(Section node) throws InterruptedException {
        this.sections.add(node);
        super.visit(node);
        this.sections.pop();
        return null;
    }


    @Override
    public Void visit(Question node) throws InterruptedException {

        QuestionData questionData = this.getQuestionData(node.getName());

        if(questionData != null) {
            Style style = new Style();
            style.setPage(this.page.getName());
            style.setSection(getCurrentSection());

            if(node.getWidget() != null)
                style.setWidget(convertToStyleWidget(node.getWidget()));

            WidgetType widgetType = node.getWidget() != null ? node.getWidget().getWidgetType() : getDefaultWidgetType(questionData.getNodeType());
            style.fillNullFields(getParentStyles(widgetType));
            questionData.setWidgetType(widgetType);

            questionData.setStyle(style);
            this.outputResult.add(questionData);
        }

        return null;
    }

    private Style getParentStyles(WidgetType widgetType) throws InterruptedException {
        Style style = new Style();

        ListIterator<Section> li = this.sections.listIterator(this.sections.size());
        while(li.hasPrevious()) {
            Style defaultStyle = this.defaultStyleEvaluator.findStyle(li.previous(), widgetType);
            style.fillNullFields(defaultStyle);
        }
        Style pageStyle = this.defaultStyleEvaluator.findStyle(this.page, widgetType);
        style.fillNullFields(pageStyle);
        return style;
    }

    private List<String> getCurrentSection() {
        List<String> sections = new ArrayList<>();
        for(Section section : this.sections)
            sections.add(section.getName());
        return sections;
    }

    private WidgetType getDefaultWidgetType(NodeType nodeType) {
        return WidgetType.valueOf(nodeType.toString());
    }

    /**
     *
     * @param node
     * @return
     */
    private WidgetParameters convertToStyleWidget(Widget node) {
        List<String> parameters = new ArrayList<>();
        for(Parameter parameter : node.getParameters())
            parameters.add(parameter.getParameter());
        return new WidgetParameters(parameters);
    }
}
