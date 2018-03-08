package expression.unary;

import analysis.SymbolTable;
import expression.Expression;
import expression.variable.ExpressionVariable;
import expression.ReturnType;
import expression.binary.ExpressionArithmeticMultiply;
import expression.variable.ExpressionVariableNumber;

import java.math.BigDecimal;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Expression e) {
        super(e, "-");
    }

    @Override
    public ExpressionVariable evaluate(SymbolTable symbolTable) {
        ExpressionVariableNumber leftEvaluated = new ExpressionVariableNumber(new BigDecimal(-1));
        Expression rightEvaluated = this.expression.evaluate(symbolTable);
        return new ExpressionArithmeticMultiply(leftEvaluated, rightEvaluated).evaluate(symbolTable);
    }

    @Override
    public ReturnType getReturnType(SymbolTable symbolTable) {
        return this.expression.getReturnType(symbolTable);
    }

    @Override
    public void typeCheck(SymbolTable symbolTable) {
        this.expression.typeCheck(symbolTable);

        if (!this.expression.getReturnType(symbolTable).neg()) {
            throw new IllegalArgumentException("Cannot apply operator - to '"
                    + this.expression.getReturnType(symbolTable) + "'");
        }
    }
}