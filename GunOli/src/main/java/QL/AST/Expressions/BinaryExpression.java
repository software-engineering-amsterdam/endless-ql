package QL.AST.Expressions;

public abstract class BinaryExpression extends Expression {
    private Expression exprLeft;
    private Expression exprRight;
    private String operator;

    public BinaryExpression(String operator, Expression exprLeft, Expression exprRight, int lineNumber){
        super(lineNumber);
        this.exprRight = exprRight;
        this.exprLeft = exprLeft;
        this.operator = operator;
    }

    public Expression getExprLeft() {
        return exprLeft;
    }

    public Expression getExprRight() {
        return exprRight;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return this.exprLeft.toString() + this.operator + this.exprRight.toString();
    }
}
