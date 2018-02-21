package main.org.uva.ql.ast.expression.binary;

import main.org.uva.ql.ast.expression.Expression;

public class And extends BinaryOperation {

    public And(Expression left, Expression right){
        super(left,right);
    }

    @Override
    public String toString() {
        return String.format("(%s AND %s)", this.getLeft(), this.getRight());
    }
}
