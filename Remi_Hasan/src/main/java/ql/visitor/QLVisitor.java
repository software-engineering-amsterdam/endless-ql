package ql.visitor;

import ql.model.Form;
import ql.model.expression.Expression;
import ql.model.expression.Identifier;
import ql.model.expression.binary.*;
import ql.model.expression.constant.*;
import ql.model.expression.unary.NegationExpression;
import ql.model.expression.unary.NotExpression;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Question;
import ql.model.statement.Statement;

public class QLVisitor<T> implements IQLVisitor<T> {

    private T visitBinaryExpression(BinaryExpression expression) {
        expression.getLeft().accept(this);
        expression.getRight().accept(this);
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
    public T visit(DivisionExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(MultiplicationExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(SubtractionExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(SumExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(EqualExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(GreaterEqualExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(GreaterThanExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(LessEqualExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(LessThanExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(AndExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(OrExpression expression) {
        return this.visitBinaryExpression(expression);
    }

    @Override
    public T visit(NotExpression expression) {
        return expression.getOperand().accept(this);
    }

    @Override
    public T visit(NegationExpression expression) {
        return expression.getOperand().accept(this);
    }

    @Override
    public T visit(BooleanConstant constant) {
        return null;
    }

    @Override
    public T visit(DateConstant constant) {
        return null;
    }

    @Override
    public T visit(IntegerConstant constant) {
        return null;
    }

    @Override
    public T visit(DecimalConstant constant) {
        return null;
    }

    @Override
    public T visit(MoneyConstant constant) {
        return null;
    }

    @Override
    public T visit(StringConstant constant) {
        return null;
    }

    @Override
    public T visit(UndefinedConstant constant) {
        return null;
    }

    @Override
    public T visit(Identifier identifier) {
        return null;
    }
}
