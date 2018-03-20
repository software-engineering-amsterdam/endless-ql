package qlviz.model.style;

import java.math.BigDecimal;

public class NumericParameter implements Parameter {

    private final BigDecimal value;

    public NumericParameter(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void accept(ParameterVisitor voidParameterVisitor) {
        voidParameterVisitor.visit(this);
    }
}
