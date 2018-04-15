package QL.AST.Expressions;

public abstract class UnaryExpression extends Expression{
    private Expression expr;
    private String operator;

    public UnaryExpression(String operator, Expression expr, int lineNumber){
        super(lineNumber);
        this.operator = operator;
        this.expr = expr;
    }

    public Expression getExpression() {
        return expr;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString(){
        return this.operator + getExpression().toString();
    }
}
