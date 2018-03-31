package ql.model.expression.constant;

import ql.model.expression.ReturnType;
import ql.visitor.IQLVisitor;

public class UndefinedConstant extends Constant<ReturnType> {

    public UndefinedConstant(ReturnType value) {
        // Store the question type corresponding to the undefined value, so we can type check it
        super(value);
    }

    public ReturnType getReturnType() {
        return this.getValue();
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}