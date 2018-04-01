package ql.model.expression.constant;

import ql.visitor.IQLVisitor;

public class BooleanConstant extends Constant<Boolean> {

    public BooleanConstant(Boolean value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}