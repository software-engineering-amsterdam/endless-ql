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

public final class ApplyQLSStyle extends BaseStyleASTVisitor<Void> {

    private final Fetcher defaultStyleEvaluator = new Fetcher();
    private final Stack<Section> currentSections = new Stack<>();
    private EvaluationResult qlInputResult = null;
    private EvaluationResult outputResult = null;

    //Current state for helper Visitor
    private Page currentPage = null;


    private ApplyQLSStyle() {

    }

    private EvaluationResult applyStyle(final EvaluationResult interpreterResult, final Stylesheet stylesheet) {
        this.qlInputResult = interpreterResult;
        this.outputResult = new EvaluationResult(new ArrayList<>(), interpreterResult.getMessages(), interpreterResult.getAst());
        //The helper will fill the outputResult
        stylesheet.accept(this);
        return this.outputResult;
    }

    private QuestionData getQLQuestionData(final String questionName) {
        for (final QuestionData questionData : this.qlInputResult.getQuestions()) {
            if (questionData.getQuestionName().equals(questionName)) {
                return questionData;
            }
        }

        return null;
    }

    @Override
    public Void visit(final Page node) {
        this.currentPage = node;
        return super.visit(node);
    }

    @Override
    public Void visit(final Section node) {
        this.currentSections.add(node);
        super.visit(node);
        this.currentSections.pop();
        return null;
    }

    @Override
    public Void visit(final Question node) {
        final QuestionData questionData = this.getQLQuestionData(node.getName());

        if (questionData != null) {
            if (node.getWidget() != null) {
                questionData.setWidgetType(node.getWidget().getWidgetType());
            }

            questionData.setStyle(this.getQuestionStyle(node, questionData.getNodeType()));
            this.outputResult.add(questionData);
        }

        return null;
    }

    private Style getQuestionStyle(final Question question, final NodeType nodeType) {
        final Style style = new Style();
        style.setPage(this.currentPage.getName());
        style.setSection(this.getCurrentSection());

        if (question.getWidget() != null)
            style.setWidget(new QLWidget(question.getWidget().getWidgetType(), question.getWidget().getParametersAsStrings()));

        final Style styleFromStylesheet = this.defaultStyleEvaluator.getCascadingStyle(nodeType, this.currentSections, this.currentPage);
        style.fillNullFields(styleFromStylesheet);
        return style;
    }

    private List<String> getCurrentSection() {
        final List<String> sections = new ArrayList<>();
        for (final Section section : this.currentSections)
            sections.add(section.getName());
        return sections;
    }

    public static class Linker {
        public final EvaluationResult apply(final EvaluationResult interpreterResult, final Stylesheet stylesheet) {
            final ApplyQLSStyle interpreter = new ApplyQLSStyle();
            return interpreter.applyStyle(interpreterResult, stylesheet);
        }
    }
}
