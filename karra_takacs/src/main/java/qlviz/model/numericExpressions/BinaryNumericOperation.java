package qlviz.model.numericExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.Node;

import java.math.BigDecimal;

public class BinaryNumericOperation extends Node implements NumericExpression {


    private final NumericExpression leftSide;
    private final NumericExpression rightSide;
    private final BinaryNumericOperator operator;

    public BinaryNumericOperation(NumericExpression leftSide, NumericExpression rightSide, BinaryNumericOperator operator, ParserRuleContext context) {
        super(context);
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
