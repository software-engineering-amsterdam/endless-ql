package gui.builder;

import gui.model.GUIQuestion;
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

public class GUIQuestionsBuilder extends QLBaseVisitor<List<GUIQuestion>> {
    private final Expression condition;

    public GUIQuestionsBuilder(Expression condition) {
        // Condition to be set for all questions that are visited
        this.condition = condition;
    }

    @Override
    public List<GUIQuestion> visit(Statement statement) {
        return statement.accept(this);
    }

    @Override
    public List<GUIQuestion> visit(IfBlock ifBlock) {
        List<GUIQuestion> guiQuestions = new ArrayList<>();

        // If blocks can be nested, so chain conditions
        Expression ifCondition = new ExpressionLogicalAnd(null, this.condition, ifBlock.getCondition());
        GUIQuestionsBuilder trueStatementVisitor = new GUIQuestionsBuilder(ifCondition);

        for (Statement statement : ifBlock.getTrueStatements()) {
            List<GUIQuestion> guiQuestionList = statement.accept(trueStatementVisitor);
            guiQuestions.addAll(guiQuestionList);
        }

        return guiQuestions;
    }

    @Override
    public List<GUIQuestion> visit(IfElseBlock ifElseBlock) {
        // Visit if block
        List<GUIQuestion> guiQuestions = this.visit((IfBlock) ifElseBlock);

        // Else block, so negate invert condition
        Expression elseCondition = new ExpressionLogicalAnd(null, this.condition,
                new ExpressionUnaryNot(null, ifElseBlock.getCondition()));
        GUIQuestionsBuilder falseStatementVisitor = new GUIQuestionsBuilder(elseCondition);
        for (Statement statement : ifElseBlock.getFalseStatements()) {
            List<GUIQuestion> guiQuestionList = statement.accept(falseStatementVisitor);
            guiQuestions.addAll(guiQuestionList);
        }

        return guiQuestions;
    }

    @Override
    public List<GUIQuestion> visit(Question question) {
        List<GUIQuestion> guiQuestions = new ArrayList<>();

        GUIQuestion guiQuestion = new GUIQuestion(question.identifier, question.label, question.type, this.condition, question.isComputed(), question.computedAnswer);
        guiQuestions.add(guiQuestion);

        return guiQuestions;
    }
}