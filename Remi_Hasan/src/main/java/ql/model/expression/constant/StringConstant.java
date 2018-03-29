package ql.model.expression.constant;

import ql.visitor.IQLVisitor;

public class StringConstant extends Constant<String> {

    public StringConstant(String value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}