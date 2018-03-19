package logic.collectors;

import ast.model.Form;
import ast.model.expressions.Expression;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.unary.logical.Negation;
import ast.model.statements.IfStatement;
import ast.model.statements.Question;
import ast.model.statements.Statement;
import ast.visitors.AbstractASTTraverse;
import gui.model.QuestionModel;
import logic.type.MixedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CollectQuestionModelsVisitor extends AbstractASTTraverse {

    private List<QuestionModel> questionModels = new ArrayList<>();
    private Stack<Expression> conditionsStack = new Stack<>();

    public List<QuestionModel> getQuestionModels(Form form) {
        this.questionModels = new ArrayList<>();
        this.conditionsStack = new Stack<>();
        form.accept(this);
        return questionModels;
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question question) {

        Expression aggregatedVisibilityCondition = this.aggregateConditionsStack();

        // strips question and flattens visibility condition (for gui rendering ease)
        QuestionModel questionModel = new QuestionModel(
                question.getLabel(),
                question.getVariableName(),
                question.getVariableType(),
                aggregatedVisibilityCondition,
                question.getAssignedExpression()
        );

        this.questionModels.add(questionModel);

        question.getVariableType().accept(this);
        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        ifStatement.getCondition().accept(this);

        this.conditionsStack.push(ifStatement.getCondition());

        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }

        // flip the condition on the stack to negation
        this.conditionsStack.push(new Negation(this.conditionsStack.pop()));

        for (Statement statement : ifStatement.getElseStatementList()) {
            statement.accept(this);
        }

        this.conditionsStack.pop();

        return null;
    }

    private Expression aggregateConditionsStack() {

        Expression finalExpression = null;

        for (Expression expression : this.conditionsStack) {
            if (finalExpression == null) {
                finalExpression = expression;
            } else {
                finalExpression = new LogicalAnd(finalExpression, expression);
            }
        }

        return finalExpression;
    }

    public HashMap<String, MixedValue> getVariablesValues() {

        HashMap<String, MixedValue> variablesValues = new HashMap<>();
        for (QuestionModel questionModel : this.questionModels) {
            variablesValues.put(questionModel.getVariableName(), questionModel.getValue());
        }
        return variablesValues;
    }
}
