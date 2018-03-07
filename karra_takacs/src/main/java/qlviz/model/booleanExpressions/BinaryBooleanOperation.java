package qlviz.model.booleanExpressions;


import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.Node;

public class BinaryBooleanOperation extends Node implements BooleanExpression {


    private final BooleanExpression leftSide;
    private final BooleanExpression rightSide;
    private final BinaryBooleanOperator operator;

    public BinaryBooleanOperation(BooleanExpression leftSide, BooleanExpression rightSide, BinaryBooleanOperator operator, ParserRuleContext context) {
        super(context);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }


    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public BooleanExpression getLeftSide() {
        return leftSide;
    }

    public BooleanExpression getRightSide() {
        return rightSide;
    }

    public BinaryBooleanOperator getOperator() {
        return operator;
    }
}
