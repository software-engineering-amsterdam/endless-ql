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

        Expression aggregatedExpression = this.aggregateConditionsStack();

        this.formBlocks.add(new FormBlock(
                question.getLabel(),
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

        ifStatement.getCondition().accept(this);

        this.conditionsStack.push(ifStatement.getCondition());

        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }

        // flip the condition on the stack to negation @TODO: pretiffy metainformation, Expression is not necessary an AST node - can be, but doesn't have to... think about it.
        this.conditionsStack.push(new Negation(this.conditionsStack.pop(), new ASTNode.MetaInformation(null, null, null, "!(" + ifStatement.getCondition().getMetaInformation().getText() + ")")));

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
                // @TODO: idem
                finalExpression = new LogicalAnd(finalExpression, expression, new ASTNode.MetaInformation(0, 0, 0, "(" + finalExpression.getMetaInformation().getText() + ") && (" + expression.getMetaInformation().getText() + ")"));
            }
        }

        return finalExpression;
    }
}
