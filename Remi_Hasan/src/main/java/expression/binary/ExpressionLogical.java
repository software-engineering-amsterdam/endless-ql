package expression.binary;

import analysis.SymbolTable;
import expression.Expression;
import expression.ReturnType;

public abstract class ExpressionLogical extends ExpressionBinary {

    ExpressionLogical(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType(SymbolTable symbolTable) {
        return ReturnType.BOOLEAN;
    }
}