package ql.model.expression.constant;

import ql.model.expression.ReturnType;
import ql.visitor.IQLVisitor;

public class UndefinedConstant extends Constant<ReturnType> {

    public UndefinedConstant(ReturnType value) {
        super(value);
    }

    public ReturnType getReturnType() {
        return this.value;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}