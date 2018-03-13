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
import gui.model.FormQuestionHolder;
import gui.model.MixedValueHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CollectFormQuestionHoldersVisitor extends AbstractASTTraverse {

    private List<FormQuestionHolder> formQuestionHolders = new ArrayList<>();
    private Stack<Expression> conditionsStack = new Stack<>();

    public List<FormQuestionHolder> getFormQuestionHolders() {
        return formQuestionHolders;
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
        FormQuestionHolder formQuestionHolder = new FormQuestionHolder(
                question.getLabel(),
                question.getVariableName(),
                question.getVariableType(),
                aggregatedVisibilityCondition,
                question.getAssignedExpression()
        );

        this.formQuestionHolders.add(formQuestionHolder);

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

    public HashMap<String, MixedValueHolder> getVariablesValues() {

        HashMap<String, MixedValueHolder> variablesValues = new HashMap<>();
        for (FormQuestionHolder formQuestionHolder : this.formQuestionHolders) {
            variablesValues.put(formQuestionHolder.getVariableName(), formQuestionHolder.getValueHolder());
        }
        return variablesValues;
    }
}
