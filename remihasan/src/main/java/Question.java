import java.util.ArrayList;

public class Question {

    private ArrayList<Condition> conditions = new ArrayList<Condition>();
    private String name;
    private String text;
    private Answer answer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Question[text: " + this.text + ", answerType: " + answer.toString() + "]");

        return stringBuilder.toString();
    }
}
