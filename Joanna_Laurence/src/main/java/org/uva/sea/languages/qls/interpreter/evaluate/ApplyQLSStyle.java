package org.uva.sea.languages.qls.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QLWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.qls.interpreter.evaluate.EvaluateDefaultStyle.Fetcher;
import org.uva.sea.languages.qls.parser.elements.Page;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.elements.specification.Section;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ApplyQLSStyle extends BaseStyleASTVisitor<Void> {

    private final Fetcher defaultStyleEvaluator = new Fetcher();
    private final Stack<Section> currentSections = new Stack<>();
    private EvaluationResult qlInputResult = null;
    private EvaluationResult outputResult = null;

    //Current state for visitor Visitor
    private Page currentPage = null;


    private ApplyQLSStyle() {

    }

    private EvaluationResult applyStyle(EvaluationResult interpreterResult, Stylesheet stylesheet) {
        this.qlInputResult = interpreterResult;
        this.outputResult = new EvaluationResult(new ArrayList<>(), interpreterResult.getMessages(), interpreterResult.getAst());
        //The visitor will fill the outputResult
        stylesheet.accept(this);
        return this.outputResult;
    }

    private QuestionData getOriginalQuestionData(String questionName) {
        for (QuestionData questionData : this.qlInputResult.getQuestions()) {
            if (questionData.getQuestionName().equals(questionName)) {
                return questionData;
            }
        }

        return null;
    }

    @Override
    public Void visit(Page node) {
        this.currentPage = node;
        return super.visit(node);
    }

    @Override
    public Void visit(Section node) {
        this.currentSections.add(node);
        super.visit(node);
        this.currentSections.pop();
        return null;
    }

    @Override
    public Void visit(Question node) {
        QuestionData questionData = this.getOriginalQuestionData(node.getName());

        if (questionData != null) {
            if (node.getWidget() != null) {
                questionData.setWidgetType(node.getWidget().getWidgetType());
            }

            questionData.setStyle(this.getQuestionStyle(node, questionData.getNodeType()));
            this.outputResult.add(questionData);
        }

        return null;
    }

    private Style getQuestionStyle(Question question, NodeType nodeType) {
        Style style = new Style();
        style.setPage(this.currentPage.getName());
        style.setSection(this.getCurrentSection());

        if (question.getWidget() != null)
            style.setWidget(new QLWidget(question.getWidget().getWidgetType(), question.getWidget().getParametersAsStrings()));

        style.fillNullFields(this.defaultStyleEvaluator.getCascadingStyle(nodeType, this.currentSections, this.currentPage));
        return style;
    }

    private List<String> getCurrentSection() {
        List<String> sections = new ArrayList<>();
        for (Section section : this.currentSections)
            sections.add(section.getName());
        return sections;
    }

    public static class Linker {
        public EvaluationResult apply(EvaluationResult interpreterResult, Stylesheet stylesheet) {
            ApplyQLSStyle interpreter = new ApplyQLSStyle();
            return interpreter.applyStyle(interpreterResult, stylesheet);
        }
    }
}
