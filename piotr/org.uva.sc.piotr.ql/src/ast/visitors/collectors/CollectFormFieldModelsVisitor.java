package ast.visitors.collectors;

import ast.model.ASTNode;
import ast.model.Form;
import ast.model.expressions.Expression;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.unary.logical.Negation;
import ast.model.statements.IfStatement;
import ast.model.statements.Question;
import ast.model.statements.Statement;
import ast.visitors.AbstractASTTraverse;
import ast.visitors.evaluators.ExpressionResult;
import gui.model.FormQuestion;

import java.util.*;

public class CollectFormFieldModelsVisitor extends AbstractASTTraverse {

    private List<FormQuestion> formQuestions = new ArrayList<>();
    private Stack<Expression> conditionsStack = new Stack<>();

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

        FormQuestion formQuestion = new FormQuestion(
                question.getLabel(),
                question.getVariableName(),
                question.getVariableType().toDataType(),
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

        // flip the condition on the stack to negation @TODO: prettify meta-information, Expression is not necessary an AST node - can be, but doesn't have to... think about it.
        this.conditionsStack.push(new Negation(this.conditionsStack.pop(), new ASTNode.MetaInformation(0, 0, 0, "!(" + ifStatement.getCondition().getMetaInformation().getText() + ")")));

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
                // @TODO: prettify meta-information
                finalExpression = new LogicalAnd(finalExpression, expression, new ASTNode.MetaInformation(0, 0, 0, "(" + finalExpression.getMetaInformation().getText() + ") && (" + expression.getMetaInformation().getText() + ")"));
            }
        }

        return finalExpression;
    }

    public HashMap<String, ExpressionResult> getVariablesValues() {

        HashMap<String, ExpressionResult> variablesValues = new HashMap<>();
        for (FormQuestion formQuestion : this.formQuestions) {
            variablesValues.put(formQuestion.getVariableName(), formQuestion.getValue());
        }
        return variablesValues;
    }
}
