package ql.ast.expression;

import ql.ast.type.Type;

public class UnaryOperation extends Operation {
    
    private final Class<? extends UnaryOperator> operator;
    private final Class<? extends Type> expr;
    
    public UnaryOperation(UnaryOperator operator,Class<? extends Type> expr) {
        this.operator   = operator.getClass();
        this.expr       = expr;
    }
    
    public UnaryOperation(UnaryOperator operator,Type expr) {
        this.operator   = operator.getClass();
        this.expr       = expr.getClass();
    }
    
    public Class<? extends UnaryOperator> getOperator() {
        return operator;
    }
    
    public Class<? extends Type> getExpr() {
        return expr;
    }

    @Override
    public boolean equals(Operation query) {
        return query.equals(this);
    }
    
    @Override
    public boolean equals(UnaryOperation rule) {
        return  rule.getOperator().isAssignableFrom(operator) && 
                rule.getExpr().isAssignableFrom(expr);
    }
}
