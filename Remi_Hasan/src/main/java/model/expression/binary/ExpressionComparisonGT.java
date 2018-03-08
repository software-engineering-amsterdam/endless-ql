package model.expression.binary;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionBinary;
import org.antlr.v4.runtime.Token;

public class ExpressionComparisonGT extends ExpressionBinary<Boolean> {

    public ExpressionComparisonGT(Token start, Expression left, Expression right) {
        super(start, left, right);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionComparisonGT;
    }
}
