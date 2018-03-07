package expression.variable;

import expression.ExpressionVariable;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    ExpressionVariableDecimal(Double value) {
        super(value);
    }

//    @Override
//    public Value<Double> accept(BaseASTVisitor visitor) {
//        return null;
//    }

    @Override
    public DecimalValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}
