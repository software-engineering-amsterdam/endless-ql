package ql.evaluation;

import ql.evaluation.value.*;
import ql.model.expression.Expression;
import ql.model.expression.Identifier;
import ql.model.expression.binary.*;
import ql.model.expression.constant.*;
import ql.model.expression.unary.NegationExpression;
import ql.model.expression.unary.NotExpression;
import ql.visitor.QLVisitor;

public class ExpressionEvaluator extends QLVisitor<Value> {

    private SymbolTable symbolTable;

    public ExpressionEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Value visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public Value visit(DivisionExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.divide(rightInterpreted);
    }

    @Override
    public Value visit(MultiplicationExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.multiply(rightInterpreted);
    }

    @Override
    public Value visit(SubtractionExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.subtract(rightInterpreted);
    }

    @Override
    public Value visit(SumExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.sum(rightInterpreted);
    }

    @Override
    public Value visit(EqualExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.eq(rightInterpreted);
    }

    @Override
    public Value visit(GreaterEqualExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.ge(rightInterpreted);
    }

    @Override
    public Value visit(GreaterThanExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.gt(rightInterpreted);
    }

    @Override
    public Value visit(LessEqualExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.le(rightInterpreted);
    }

    @Override
    public Value visit(LessThanExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.lt(rightInterpreted);
    }

    @Override
    public Value visit(AndExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.and(rightInterpreted);
    }

    @Override
    public Value visit(OrExpression expression) {
        Value leftInterpreted = expression.getLeft().accept(this);
        Value rightInterpreted = expression.getRight().accept(this);
        return leftInterpreted.or(rightInterpreted);
    }

    @Override
    public Value visit(NotExpression expression) {
        Value valueInterpreted = expression.getOperand().accept(this);
        return valueInterpreted.not();
    }

    @Override
    public Value visit(NegationExpression expression) {
        Value valueInterpreted = expression.getOperand().accept(this);
        return valueInterpreted.neg();
    }

    @Override
    public Value visit(BooleanConstant expression) {
        return new BooleanValue(expression.value);
    }

    @Override
    public Value visit(DateConstant expression) {
        return new DateValue(expression.value);
    }

    @Override
    public Value visit(IntegerConstant constant) {
        return new NumberValue(constant.value);
    }

    @Override
    public Value visit(DecimalConstant constant) {
        return new NumberValue(constant.value);
    }

    @Override
    public Value visit(MoneyConstant constant) {
        return new NumberValue(constant.value);
    }

    @Override
    public Value visit(StringConstant constant) {
        return new StringValue(constant.value);
    }

    @Override
    public Value visit(UndefinedConstant constant) {
        return new UndefinedValue();
    }

    @Override
    public Value visit(Identifier identifier) {
        return this.symbolTable.getExpression(identifier.getIdentifier()).accept(this);
    }
}