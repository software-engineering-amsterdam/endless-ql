package ql.ast.expression;

public abstract class UnaryOperator extends Operator {
    
    protected Expression operand;
        
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
