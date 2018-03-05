package expression.unary;

import analysis.SymbolTable;
import expression.Expression;
import expression.ReturnType;

public abstract class ExpressionUnary extends Expression {
    final Expression expression;
    final private String opString;

    ExpressionUnary(Expression expression, String opString) {
        this.expression = expression;
        this.opString = opString;
    }

    public abstract ReturnType getReturnType(SymbolTable symbolTable);

    @Override
    public String toString() {
        return opString + this.expression.toString();
    }
}