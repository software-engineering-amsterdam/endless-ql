package ql.ast.expression;

import ql.ast.type.Type;
import ql.visitors.checker.checkers.ExpressionVisitorType;

public abstract class Operator extends Expression {
    
    public abstract String getOperator();

    public Type getType() {
        return this.accept(new ExpressionVisitorType());
    }
}
