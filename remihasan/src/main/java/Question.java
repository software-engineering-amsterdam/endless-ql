public class Question {

    private final String name;
    private final String text;
    private Answer answer;

    Question(String name, String text, Answer answer){
        this.name = name;
        this.text = text;
        this.answer = answer;
    }

    public Answer getAnswer(){
        return answer;
    }
}
