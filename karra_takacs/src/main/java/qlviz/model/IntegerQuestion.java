package qlviz.model;

import java.util.Date;

public class IntegerQuestion extends Question {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IntegerQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }
}

