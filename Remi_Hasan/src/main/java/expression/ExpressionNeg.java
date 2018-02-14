package expression;

import model.Form;

public class ExpressionNeg extends Expression<Double> {
    private final Expression value;

    public ExpressionNeg(Expression value) {
        this.value = value;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.value.getReturnType(form) == ReturnType.Number && this.value.isEvaluable(form);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }

    @Override
    public Double evaluate(Form form) {
        if(isEvaluable(form)){
            ExpressionArithmeticMultiply rewrittenExpression = new ExpressionArithmeticMultiply(new ExpressionVariableDecimal(-1.0), this.value);
            return rewrittenExpression.evaluate(form);
        }
        return null;
    }

    @Override
    public Double divide(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.divide(form, other);
    }

    @Override
    public Double multiply(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.multiply(form, other);
    }

    @Override
    public Double subtract(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.subtract(form, other);
    }

    @Override
    public Double sum(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.sum(form, other);
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.equals(form, other);
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.ge(form, other);
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.gt(form, other);
    }

    @Override
    public Boolean le(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.le(form, other);
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        ExpressionVariableDecimal rewrittenExpression = new ExpressionVariableDecimal(this.evaluate(form));
        return rewrittenExpression.lt(form, other);
    }
}