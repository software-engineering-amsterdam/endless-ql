package classes;

public class Question<T> {
    private String questionText;
    private T value;

    public Question(String questionText, T value){
        this.questionText = questionText;
        this.value = value;
    }

    public String getText(){
        return questionText;
    }

    public T getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value.getClass().getName() + ": " + questionText;
    }
}
