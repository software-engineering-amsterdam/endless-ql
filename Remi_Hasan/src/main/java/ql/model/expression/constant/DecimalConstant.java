package ql.model.expression.constant;

import ql.visitor.IQLVisitor;

public class DecimalConstant extends Constant<Double> {

    public DecimalConstant(Double value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
