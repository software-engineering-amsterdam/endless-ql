package ql.model.expression.constant;

import ql.visitor.IQLVisitor;

import java.math.BigDecimal;

public class MoneyConstant extends Constant<BigDecimal> {

    public MoneyConstant(String value) {
        super(new BigDecimal(value));
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
