package ql.ast.expression;

import ql.ast.type.Type;
import ql.evaluator.value.Date;
import ql.evaluator.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class DateLiteral extends Literal {

    private Date value;
    
    public DateLiteral() { 
        this.value = new Date();
    }
    
    public DateLiteral(String value) {
        this.value = new Date(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Date();
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
