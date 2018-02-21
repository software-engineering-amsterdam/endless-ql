package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Int;
import ql.ast.type.Numeric;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class And extends BinaryBooleanLogic {

    public And(Expression lhs, Expression rhs) {
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
        return "&&";
    }

    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Int.class, Numeric.class));
    }
}
