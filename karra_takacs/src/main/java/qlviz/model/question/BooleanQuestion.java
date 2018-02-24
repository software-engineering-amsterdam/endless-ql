package qlviz.model.question;

public class BooleanQuestion extends Question {
    private boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public BooleanQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }

    @Override
    public void accept(QuestionVisitor visitor) {
        visitor.visit(this);
    }
}

