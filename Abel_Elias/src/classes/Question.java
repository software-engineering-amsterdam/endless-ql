package classes;

import classes.expressions.ExpressionType;

public class Question<T> {
    private String questionText;
    private T value;
    private Class type;
    private boolean fixed;

    public Question(CodeBlock code, String questionText, T value, boolean fixed){
        this.fixed = fixed;
        this.value = value;
        System.out.println(value);
        this.type = value.getClass();
        this.questionText = questionText;
    }

    public String getText(){
        return questionText;
    }

    public T getValue(){
        return value;
    }

    public ExpressionType getTypeName() {
        String typeText = type.getSimpleName();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
        return ExpressionType.valueOf(typeText);
    }

    public Question setValue(Object value){
        this.value = (T) value;
        return this;
    }

    public boolean isFixed(){
        return this.fixed;
    }

    public Class getType(){
        return this.type;
    }


}
