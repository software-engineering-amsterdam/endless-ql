package classes;

import classes.expressions.ExpressionType;
import classes.types.Value;

public class Question<T>{
    private String questionText;
    private T value;
    private Value type;
    private boolean isVisible;
    private boolean fixed;

    public Question(CodeBlock code, String questionText, Value type, T value, boolean fixed, boolean isVisible){
        this.fixed = fixed;
        this.value = value;
        this.type = value.getClass();
        this.questionText = questionText;
        this.isVisible = isVisible;
    }

    public String getText(){
        return questionText;
    }

    public T getValue(){
        return value;
    }

//    public ExpressionType getTypeName() {
//        String typeText = type.getSimpleName();
//        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
//        return ExpressionType.valueOf(typeText);
//    }

    public Value getType() {
        return this.type;
    }

    public Question setValue(Object value){
        this.value = (T) value;
        return this;
    }

    public boolean getVisibility() {
        return this.isVisible;
    }

    public boolean isFixed(){
        return this.fixed;
    }

    //public Class getType(){
       // return this.type;
    //}


}
