package ql.model.expression.constant;

import ql.visitor.IQLVisitor;

public class IntegerConstant extends Constant<Integer> {

    public IntegerConstant(Integer value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
