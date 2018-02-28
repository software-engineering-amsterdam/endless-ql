package qlviz.model.booleanExpressions;


import qlviz.interpreter.linker.BooleanExpressionVisitor;

public class BinaryBooleanOperation implements BooleanExpression {


    private final BooleanExpression leftSide;
    private final BooleanExpression rightSide;
    private final BinaryBooleanOperator operator;

    public BinaryBooleanOperation(BooleanExpression leftSide, BooleanExpression rightSide, BinaryBooleanOperator operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }

    @Override
    public boolean evaluate() {
        switch (this.operator) {
            case And:
                return this.leftSide.evaluate() && this.rightSide.evaluate();
            case Or:
                return this.leftSide.evaluate() || this.rightSide.evaluate();
        }
        return false;
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public BooleanExpression getLeftSide() {
        return leftSide;
    }

    public BooleanExpression getRightSide() {
        return rightSide;
    }
}
