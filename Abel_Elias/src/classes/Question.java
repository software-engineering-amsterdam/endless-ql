package classes;

import classes.CodeBlock;

public class Question<T> {
    private String questionText;
    private T type;
    private boolean fixed;

    public Question(CodeBlock code, String questionText, T type, boolean fixed){
        this.fixed = fixed;
        this.questionText = questionText;
        this.type = type;
    }

    public String getText(){
        return questionText;
    }

    public T getType(){
        return type;
    }

    @Override
    public String toString() {
        return type.getClass().getName() + ": " + questionText;
    }
}
