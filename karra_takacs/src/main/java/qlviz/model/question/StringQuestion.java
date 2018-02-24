package qlviz.model.question;

public class StringQuestion extends Question {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }
}

