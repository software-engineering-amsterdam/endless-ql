package qlviz.model.style;

import java.math.BigDecimal;

public class NumericParameter extends Parameter {

    private final BigDecimal value;

    public NumericParameter(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
