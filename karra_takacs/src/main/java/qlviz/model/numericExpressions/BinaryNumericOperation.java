package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public class BinaryNumericOperation extends NumericExpression {


    private final NumericExpression leftSide;
    private final NumericExpression rightSide;
    private final BinaryNumericOperator operator;

    public BinaryNumericOperation(NumericExpression leftSide, NumericExpression rightSide, BinaryNumericOperator operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }

    @Override
    public BigDecimal evaluate() {
        switch (this.operator) {

            case Add:
                return this.leftSide.evaluate().add(this.rightSide.evaluate());
            case Subtract:
                return this.leftSide.evaluate().subtract(this.rightSide.evaluate());
            case Multiply:
                return this.leftSide.evaluate().multiply(this.rightSide.evaluate());
            case Divide:
                return this.leftSide.evaluate().divide(this.rightSide.evaluate());
        }
        return BigDecimal.ZERO;
    }

    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    public NumericExpression getLeftSide() {
        return leftSide;
    }

    public NumericExpression getRightSide() {
        return rightSide;
    }
}
