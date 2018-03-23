package gui.builder;

import gui.model.GUILabel;
import gui.model.GUILabelWithWidget;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import ql.QLBaseVisitor;
import ql.model.IfBlock;
import ql.model.IfElseBlock;
import ql.model.Question;
import ql.model.Statement;
import ql.model.expression.Expression;
import ql.model.expression.binary.ExpressionLogicalAnd;
import ql.model.expression.unary.ExpressionUnaryNot;

import java.util.ArrayList;
import java.util.List;

public class GUIQuestionsBuilder extends QLBaseVisitor<List<GUILabelWithWidget>> {
    private final Expression condition;

    public GUIQuestionsBuilder(Expression condition) {
        // Condition to be set for all questions that are visited
        this.condition = condition;
    }

    @Override
    public List<GUILabelWithWidget> visit(Statement statement) {
        return statement.accept(this);
    }

    @Override
    public List<GUILabelWithWidget> visit(IfBlock ifBlock) {
        List<GUILabelWithWidget> guiQuestions = new ArrayList<>();

        // If blocks can be nested, so chain conditions
        Expression ifCondition = new ExpressionLogicalAnd(null, this.condition, ifBlock.getCondition());
        GUIQuestionsBuilder trueStatementVisitor = new GUIQuestionsBuilder(ifCondition);

        for (Statement statement : ifBlock.getTrueStatements()) {
            List<GUILabelWithWidget> guiQuestionList = statement.accept(trueStatementVisitor);
            guiQuestions.addAll(guiQuestionList);
        }

        return guiQuestions;
    }

    @Override
    public List<GUILabelWithWidget> visit(IfElseBlock ifElseBlock) {
        // Visit if block
        List<GUILabelWithWidget> guiQuestions = this.visit((IfBlock) ifElseBlock);

        // Else block, so negate invert condition
        Expression elseCondition = new ExpressionLogicalAnd(null, this.condition,
                new ExpressionUnaryNot(null, ifElseBlock.getCondition()));
        GUIQuestionsBuilder falseStatementVisitor = new GUIQuestionsBuilder(elseCondition);
        for (Statement statement : ifElseBlock.getFalseStatements()) {
            List<GUILabelWithWidget> guiQuestionList = statement.accept(falseStatementVisitor);
            guiQuestions.addAll(guiQuestionList);
        }

        return guiQuestions;
    }

    @Override
    public List<GUILabelWithWidget> visit(Question question) {
        List<GUILabelWithWidget> guiQuestions = new ArrayList<>();

        GUILabel label = new GUILabel(question.label);
        GUIWidget widget = WidgetFactory.getDefaultWidget(question.identifier, question.isComputed(), question.type);
        GUILabelWithWidget guiLabelWithWidget = new GUILabelWithWidget(question.identifier, question.isComputed(), this.condition, label, widget);
        guiQuestions.add(guiLabelWithWidget);

        return guiQuestions;
    }
}
