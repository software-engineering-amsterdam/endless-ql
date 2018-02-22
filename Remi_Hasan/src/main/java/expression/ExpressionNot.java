package expression;

public class ExpressionNot extends ExpressionUnary<Boolean> {

    public ExpressionNot(Expression v) {
        super(v, "!");
    }

    @Override
    public ExpressionVariable evaluate() {
        return this.v.evaluate().not();
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }

    @Override
    public boolean equals(Object other) {
        if(this.getClass().equals(other.getClass())){
            ExpressionNeg otherExpression = (ExpressionNeg) v;
            return this.v.equals(otherExpression.v);
        }
        return false;
    }
}