package qlviz.model.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.numericExpressions.NumericExpression;

public class NumericComparison implements BooleanExpression {

    private final NumericExpression leftSide;
    private final NumericExpression rightSide;
    private final NumericComparisonOperator opeartor;
    private NumericComparisonOperator operator;

    public NumericExpression getLeftSide() {
        return leftSide;
    }

    public NumericExpression getRightSide() {
        return rightSide;
    }

    public NumericComparison(NumericExpression leftSide, NumericExpression rightSide, NumericComparisonOperator opeartor) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.opeartor = opeartor;
    }

    public boolean evaluate() {
        switch (this.opeartor) {
            case Equal:
                return this.leftSide.evaluate().equals(this.rightSide.evaluate());
            case NotEqual:
                return !this.leftSide.evaluate().equals(this.rightSide.evaluate());
            case Smaller:
                return this.leftSide.evaluate().compareTo(this.rightSide.evaluate()) < 0;
            case SmallerOrEqual:
                return this.leftSide.evaluate().compareTo(this.rightSide.evaluate()) < 0 || this.leftSide.evaluate().equals(this.rightSide.evaluate());
            case Greater:
                return this.leftSide.evaluate().compareTo(this.rightSide.evaluate()) > 0;
            case GreaterOrEqual:
                return this.leftSide.evaluate().compareTo(this.rightSide.evaluate()) > 0 || this.leftSide.evaluate().equals(this.rightSide.evaluate());
        }
        return false;
    }

    public void accept(BooleanExpressionVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public NumericComparisonOperator getOperator() {
        return operator;
    }
}
