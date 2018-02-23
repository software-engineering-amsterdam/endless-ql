package ql.ast.expression;


public abstract class UnaryOperation extends Operator {
    
    protected Expression operand;
    
    public UnaryOperation(Expression operand) {
        this.operand = operand;
    }
    
    public Expression getOperand() {
        return operand;
    }
    
    @Override
    public String toString() {
        return getOperator() + operand.toString();
    }
    
    @Override
    public boolean isUnaryOperator() {
        return true;
    }
}
