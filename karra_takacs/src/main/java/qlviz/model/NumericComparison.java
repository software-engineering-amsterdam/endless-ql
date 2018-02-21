package qlviz.model;

public class NumericComparison extends BooleanExpression {

    private final NumericExpression leftSide;
    private final NumericExpression rightSide;
    private final NumericComparisonOperator opeartor;

    public NumericComparison(NumericExpression leftSide, NumericExpression rightSide, NumericComparisonOperator opeartor) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.opeartor = opeartor;
    }

    @Override
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
}
