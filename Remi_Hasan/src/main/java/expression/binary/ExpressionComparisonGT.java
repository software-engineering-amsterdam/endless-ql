package expression.binary;

import analysis.SymbolTable;
import astvisitor.BaseASTVisitor;
import astvisitor.BoolValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionComparisonGT extends ExpressionBinary<BoolValue> {

    protected ExpressionComparisonGT(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BoolValue accept(BaseASTVisitor<BoolValue> visitor) {
        return visitor.visit(this);
    }
}
