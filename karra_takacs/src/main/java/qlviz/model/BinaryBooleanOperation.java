package qlviz.model;


public class BinaryBooleanOperation extends BooleanExpression {


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
}
