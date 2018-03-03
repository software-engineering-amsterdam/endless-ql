package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;

import java.math.BigDecimal;

public class BinaryNumericOperation implements NumericExpression {


    private final NumericExpression leftSide;
    private final NumericExpression rightSide;
    private final BinaryNumericOperator operator;

    public BinaryNumericOperation(NumericExpression leftSide, NumericExpression rightSide, BinaryNumericOperator operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }


    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor) {
        return numericExpressionVisitor.visit(this);
    }

    public NumericExpression getLeftSide() {
        return leftSide;
    }

    public NumericExpression getRightSide() {
        return rightSide;
    }

    public BinaryNumericOperator getOperator() {
        return operator;
    }
}
