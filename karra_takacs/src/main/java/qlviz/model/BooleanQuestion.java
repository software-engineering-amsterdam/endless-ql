package qlviz.model;

import java.math.BigDecimal;

public class BooleanQuestion extends Question {
    private boolean value;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public BooleanQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }
}

