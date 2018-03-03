package ql.ast.expression.literal;

import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.evaluator.arithmetic.add.StrAdd;
import ql.evaluator.comparisons.equal.StrEqual;
import ql.evaluator.comparisons.notequal.StrNotEqual;
import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class StrLiteral extends Literal<String> implements ExpressionVisitable {

    private String value;

    public StrLiteral() {
        this.value = "";
    }

    public StrLiteral(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
    
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new Str();
    }
    
    @Override
    public Literal<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Literal<?> add(Literal<?> secondOperand) {
        return secondOperand.accept(new StrAdd(this));
    }
    
    @Override
    public Literal<?> equal(Literal<?> secondOperand) {
        return secondOperand.accept(new StrEqual(this));
    }
    
    @Override
    public Literal<?> notEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new StrNotEqual(this));
    }
}
