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
        this.text = text.substring(1, text.length() - 1);
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

    public void addConditions(ArrayList<Condition> conditions) {
        this.conditions.addAll(conditions);
    }
}
