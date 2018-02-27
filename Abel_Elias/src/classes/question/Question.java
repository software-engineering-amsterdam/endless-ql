package classes.question;

public class Question<T> {
    private String questionText;
    private T type;

    public Question(String questionText, T type){
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
