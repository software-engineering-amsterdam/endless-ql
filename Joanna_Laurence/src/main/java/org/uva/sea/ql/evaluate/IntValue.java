package org.uva.sea.ql.evaluate;

import java.math.BigDecimal;

public class IntValue extends Value {
    private int intValue;

    public IntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public Value add(IntValue value) {
        return new IntValue(this.intValue + value.getIntValue());
    }

    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(),value.getAmount().add(new BigDecimal(this.intValue)) );
    }

    public Value add(DecimalValue value) {
        return new DecimalValue(value.getDecimalValue() + this.intValue);
    }
}
