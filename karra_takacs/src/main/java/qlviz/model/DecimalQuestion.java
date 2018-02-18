package qlviz.model;

import java.math.BigDecimal;

public class DecimalQuestion extends Question {
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public DecimalQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }
}
