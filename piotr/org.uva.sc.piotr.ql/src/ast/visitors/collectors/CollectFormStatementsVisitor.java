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
import gui.model.FormBlock;

import java.util.*;


public class CollectFormStatementsVisitor extends AbstractASTTraverse {

    private List<FormBlock> formBlocks = new ArrayList<>();
    private Stack<Expression> conditionsStack = new Stack<>();

    public List<FormBlock> getFormBlocks() {
        return formBlocks;
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

        System.out.println("Visiting question: " + question.getLabel());
        Expression aggregatedExpression = this.aggregateConditionsStack();

//        if (aggregatedExpression != null) {
//            System.out.println("Expression: " + aggregatedExpression.getMetaInformation().getText());
//        } else {
//            System.out.println("Expression: none");
//        }

        this.formBlocks.add(new FormBlock(
                question.getVariableName(),
                question.getVariableType().toDataType(),
                aggregatedExpression,
                question.getAssignedExpression()
                ));

        question.getVariableType().accept(this);
        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        // enter
        ifStatement.getCondition().accept(this);

        // put the condition on the stack
        this.conditionsStack.push(ifStatement.getCondition());

        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }

        // flip the condition on the stack to negation
        this.conditionsStack.push(new Negation(this.conditionsStack.pop(), new ASTNode.MetaInformation(0, 0, 0, "!(" + ifStatement.getCondition().getMetaInformation().getText() + ")")));

        for (Statement statement : ifStatement.getElseStatementList()) {
            statement.accept(this);
        }

        // remove from stack
        this.conditionsStack.pop();

        // exit
        return null;
    }

    private Expression aggregateConditionsStack() {

        Expression finalExpression = null;

        for (Expression expression : this.conditionsStack) {
            if (finalExpression == null) {
                finalExpression = expression;
            } else {
                finalExpression = new LogicalAnd(finalExpression, expression, new ASTNode.MetaInformation(0, 0, 0, "(" + finalExpression.getMetaInformation().getText() + ") && (" + expression.getMetaInformation().getText() + ")"));
            }
        }

        return finalExpression;
    }
}
