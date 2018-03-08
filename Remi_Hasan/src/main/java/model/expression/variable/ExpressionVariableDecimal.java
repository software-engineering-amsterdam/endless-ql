package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    public ExpressionVariableDecimal(Token start, Double value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
