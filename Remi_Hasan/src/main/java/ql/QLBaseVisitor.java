package ql;

import ql.model.*;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionBinary;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

public class QLBaseVisitor<T> implements IQLVisitor<T> {

    private T visitBinaryExpression(ExpressionBinary expression) {
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    @Override
    public T visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Statement statement) {
        statement.accept(this);
        return null;
    }

    @Override
    public T visit(IfBlock ifBlock) {
        ifBlock.getCondition().accept(this);
        for (Statement statement : ifBlock.getTrueStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(IfElseBlock ifElseBlock) {
        ifElseBlock.getCondition().accept(this);
        for (Statement statement : ifElseBlock.getTrueStatements()) {
            statement.accept(this);
        }
        for (Statement statement : ifElseBlock.getFalseStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Question question) {
        if (question.isComputed()) {
            question.getComputedAnswer().accept(this);
        }
        return null;
    }

    @Override
    public T visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public T visit(ExpressionArithmeticDivide expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionArithmeticMultiply expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionArithmeticSubtract expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionArithmeticSum expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionComparisonEq expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionComparisonGE expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionComparisonGT expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionComparisonLE expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionComparisonLT expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionLogicalAnd expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionLogicalOr expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(ExpressionUnaryNot expression) {
        return expression.value.accept(this);
    }

    @Override
    public T visit(ExpressionUnaryNeg expression) {
        return expression.value.accept(this);
    }

    @Override
    public T visit(ExpressionVariableBoolean expression) {
        return null;
    }

    @Override
    public T visit(ExpressionVariableDate expression) {
        return null;
    }

    @Override
    public T visit(ExpressionVariableInteger expression) {
        return null;
    }

    @Override
    public T visit(ExpressionVariableDecimal expression) {
        return null;
    }

    @Override
    public T visit(ExpressionVariableMoney expression) {
        return null;
    }

    @Override
    public T visit(ExpressionVariableString expression) {
        return null;
    }

    @Override
    public T visit(ExpressionVariableUndefined expression) {
        return null;
    }

    @Override
    public T visit(ExpressionIdentifier expression) {
        return null;
    }
}
