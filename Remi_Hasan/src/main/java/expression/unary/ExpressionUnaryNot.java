package expression.unary;

import analysis.SymbolTable;
import expression.Expression;
import expression.variable.ExpressionVariable;
import expression.ReturnType;

public class ExpressionUnaryNot extends ExpressionUnary {

    public ExpressionUnaryNot(Expression e) {
        super(e, "!");
    }

    @Override
    public ExpressionVariable evaluate(SymbolTable symbolTable) {
        return this.expression.evaluate(symbolTable).not();
    }

    @Override
    public ReturnType getReturnType(SymbolTable symbolTable) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public void typeCheck(SymbolTable symbolTable) {
        this.expression.typeCheck(symbolTable);

        if (!this.expression.getReturnType(symbolTable).not()) {
            throw new IllegalArgumentException("Cannot apply operator ! to '" + this.expression.getReturnType(symbolTable) + "'");
        }
    }
}