package ql.ast.expression;

import ql.ast.type.Type;
import ql.evaluator.value.Bool;
import ql.evaluator.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class BoolLiteral extends Literal {

    private Bool value;
    
    public BoolLiteral() { 
        this.value = new Bool();
    }
    
    public BoolLiteral(String value) { 
        this.value = new Bool(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Bool();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value<?> evaluate() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
