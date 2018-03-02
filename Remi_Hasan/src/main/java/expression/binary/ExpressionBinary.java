package expression.binary;

import analysis.SymbolTable;
import expression.Expression;
import expression.ReturnType;

public abstract class ExpressionBinary extends Expression {
    final Expression left;
    final Expression right;
    final String opString;

    ExpressionBinary(Expression left, Expression right, String opString) {
        this.left = left;
        this.right = right;
        this.opString = opString;
    }

    public abstract ReturnType getReturnType(SymbolTable symbolTable);

    @Override
    public String toString() {
        return this.left.toString() + opString + this.right.toString();
    }
}