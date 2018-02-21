package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Less extends BinaryRelation {

    public Less(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }
    
    @Override
    public Type getType() {
        return new Bool();
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "<";
    }
}
