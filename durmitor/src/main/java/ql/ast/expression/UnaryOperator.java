package ql.ast.expression;

import ql.ast.type.Type;

public abstract class UnaryOperator extends Operator {
    
    protected Expression expr;
        
    public Expression getExpression() {
        return expr;
    }

    @Override
    public String toString() {
        return getOperator() + expr.toString();
    }
    
    @Override
    public boolean isUnary() {
        return true;
    }
    
    public boolean isLegalFor(Type type) {
        return legalOperations.contains(new UnaryOperation(this,type));
    }
}
