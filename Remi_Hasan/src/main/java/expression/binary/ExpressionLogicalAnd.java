package expression.binary;

import analysis.SymbolTable;
import expression.Expression;
import expression.ReturnType;
import expression.variable.ExpressionVariable;

public class ExpressionLogicalAnd extends ExpressionLogical {

    public ExpressionLogicalAnd(Expression left, Expression right) {
        super(left, right, "&&");
    }

    @Override
    public ExpressionVariable evaluate(SymbolTable symbolTable) {
        ExpressionVariable leftEvaluated = this.left.evaluate(symbolTable);
        ExpressionVariable rightEvaluated = this.right.evaluate(symbolTable);
        return leftEvaluated.and(rightEvaluated);
    }

    @Override
    public void typeCheck(SymbolTable symbolTable) {
        this.left.typeCheck(symbolTable);
        this.right.typeCheck(symbolTable);

        if(!left.getReturnType(symbolTable).and(right.getReturnType(symbolTable))) {
            throw new IllegalArgumentException("Cannot apply operator && to '"
                    + this.left.getReturnType(symbolTable) + "' and '" + this.right.getReturnType(symbolTable) + "'");

        }
    }
}