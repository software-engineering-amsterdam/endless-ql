package qlviz.model.question;

public abstract class Question {
    private String name;
    private String text;
    private QuestionType type;

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }

    public Question(String name, String text, QuestionType type) {
        this.name = name;
        this.text = text;
        this.type = type;
    }

    public abstract void accept(QuestionVisitor visitor);
}


