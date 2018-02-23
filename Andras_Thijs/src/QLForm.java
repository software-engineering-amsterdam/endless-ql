import java.util.List;

public class QLForm {
    private String name;
    private List<Question> questions;

    public QLForm(String name, List<Question> questions){
        this.name = name;
        this.questions = questions;
    }

    public QLForm(String name, Question question){
        this.name = name;
        this.questions.add(question);
    }

    public QLForm(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }
}