package qlviz.model.question;

import qlviz.QLParser;
import qlviz.model.Node;

public abstract class Question extends Node {
    private String name;
    private String text;
    private QuestionType type;
    private final QLParser.QuestionContext questionContext;

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }

    public Question(String name, String text, QuestionType type, QLParser.QuestionContext questionContext) {
        super(questionContext);
        this.name = name;
        this.text = text;
        this.type = type;
        this.questionContext = questionContext;
    }

    public QLParser.QuestionContext getQuestionContext() {
        return questionContext;
    }

    public abstract <T> T accept(QuestionVisitor<T> visitor);
    public abstract void accept(VoidQuestionVisitor visitor);
}


