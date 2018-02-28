package classes.statements;

import classes.CodeBlock;

public class Question<T> extends Statement{
    private String questionText;
    private T type;

    public Question(CodeBlock code, String questionText, T type){
        super(code);
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
