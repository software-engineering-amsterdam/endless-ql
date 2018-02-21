package main.org.uva.ql.ast.expression.binary;

import main.org.uva.ql.ast.expression.Expression;

public class LessThanEqualTo extends BinaryOperation {

    public LessThanEqualTo(Expression left, Expression right){
        super(left,right);
    }

    @Override
    public String toString() {
        return String.format("(%s <= %s)", this.getLeft(), this.getRight());
    }
}
