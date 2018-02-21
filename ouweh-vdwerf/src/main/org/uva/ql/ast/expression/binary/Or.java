package main.org.uva.ql.ast.expression.binary;

import main.org.uva.ql.ast.expression.Expression;

public class Or extends BinaryOperation {

    public Or(Expression left, Expression right){
        super(left,right);
    }

    @Override
    public String toString() {
        return String.format("(%s OR %s)", this.getLeft(), this.getRight());
    }
}
