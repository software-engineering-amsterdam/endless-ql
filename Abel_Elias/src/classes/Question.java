package classes;
import classes.values.Value;

public class Question<T>{
    private String questionText;
    private Value<T> value;
    private boolean fixed;
    private boolean isVisible;

    public Question(String questionText, Value<T> value, boolean fixed){
        this.fixed = fixed;
        this.value = value;
        this.questionText = questionText;
        this.isVisible = isVisible;
    }

    public Question(String questionText, Value<T> value, boolean fixed, boolean isVisible){
        this.fixed = fixed;
        this.value = value;
        this.questionText = questionText;
        this.isVisible = isVisible;
    }

    public String getText(){
        return questionText;
    }

    public Value<T> getValue(){
        return value;
    }

    public Question setValue(Value<T> value){
        this.value = value;
        return this;
    }

    public boolean getVisibility() {
        return this.isVisible;
    }

    public boolean isFixed(){
        return this.fixed;
    }
}


