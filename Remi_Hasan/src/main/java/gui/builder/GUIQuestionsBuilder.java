package gui.builder;

import gui.model.GUIQuestion;
import ql.QLVisitor;
import ql.model.expression.Expression;
import ql.model.expression.binary.ExpressionLogicalAnd;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Question;
import ql.model.statement.Statement;

import java.util.ArrayList;
import java.util.List;

// Translate QL Question objects to GUIQuestion objects, by chaining the conditions
// and storing the conditions inside the GUIQuestion
public class GUIQuestionsBuilder extends QLVisitor<List<GUIQuestion>> {
    private final Expression condition;

    GUIQuestionsBuilder() {
        // No previous condition, questions in this block get a simple TRUE condition
        this.condition = new ExpressionVariableBoolean(true);
    }

    private GUIQuestionsBuilder(Expression condition) {
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
        Expression ifCondition = new ExpressionLogicalAnd(this.condition, ifBlock.getCondition());

        // Collect all questions inside this if block and give them the condition
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
        Expression elseCondition = new ExpressionLogicalAnd(this.condition,
                new ExpressionUnaryNot(ifElseBlock.getCondition()));

        // Collect all questions inside this e;se block and give them the condition
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
        GUIQuestion guiQuestion = new GUIQuestion(question.getIdentifier(), question.getLabel(), question.getType(),
                this.condition, question.getComputedAnswer());
        guiQuestions.add(guiQuestion);

        return guiQuestions;
    }
}