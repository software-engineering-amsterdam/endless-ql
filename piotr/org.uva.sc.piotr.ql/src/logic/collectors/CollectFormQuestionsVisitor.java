package logic.collectors;

import ast.model.ASTNode;
import ast.model.Form;
import ast.model.expressions.Expression;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.unary.logical.Negation;
import ast.model.statements.IfStatement;
import ast.model.statements.Question;
import ast.model.statements.Statement;
import ast.visitors.AbstractASTTraverse;
import gui.model.FormQuestion;
import logic.type.MixedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CollectFormQuestionsVisitor extends AbstractASTTraverse {

    private final List<FormQuestion> formQuestions = new ArrayList<>();
    private final Stack<Expression> conditionsStack = new Stack<>();

    public List<FormQuestion> getFormQuestions() {
        return formQuestions;
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
        FormQuestion formQuestion = new FormQuestion(
                question.getLabel(),
                question.getVariableName(),
                question.getVariableType(),
                aggregatedVisibilityCondition,
                question.getAssignedExpression()
        );

        this.formQuestions.add(formQuestion);

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
        for (FormQuestion formQuestion : this.formQuestions) {
            variablesValues.put(formQuestion.getVariableName(), formQuestion.getValue());
        }
        return variablesValues;
    }
}
