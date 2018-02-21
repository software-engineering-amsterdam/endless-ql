package ql.ast.expression;

import ql.ast.type.Type;

public class BinaryOperation extends Operation {
    
    private final Class<? extends BinaryOperator> operator;
    private final Class<? extends Type> lhs;
    private final Class<? extends Type> rhs;
    
    public BinaryOperation(BinaryOperator operator,Class<? extends Type> lhs,Class<? extends Type> rhs) {
        this.operator   = operator.getClass();
        this.lhs        = lhs;
        this.rhs        = rhs;
    }
    
    public BinaryOperation(BinaryOperator operator, Type lhs, Type rhs) {
        this.operator   = operator.getClass();
        this.lhs        = lhs.getClass();
        this.rhs        = rhs.getClass();
    }
    
    public Class<? extends BinaryOperator> getOperator() {
        return operator;
    }
    
    public Class<? extends Type> getLhs() {
        return lhs;
    }
    
    public Class<? extends Type> getRhs() {
        return rhs;
    }
    
    @Override
    public boolean equals(Operation query) {
        return query.equals(this);
    }
    
    @Override
    public boolean equals(BinaryOperation rule) {
        return  rule.getOperator().isAssignableFrom(operator) && 
                rule.getLhs().isAssignableFrom(lhs) && 
                rule.getRhs().isAssignableFrom(rhs);
    }
}
