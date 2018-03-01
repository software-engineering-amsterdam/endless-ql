package domain.model.question;

public class QuestionStructure {
    private String label;
    private QuestionVariable questionVariable;

    public QuestionStructure(String label, QuestionVariable questionVariable){
        this.label = label;
        this.questionVariable = questionVariable;
    }

    public String getLabel() {
        return label;
    }

    public QuestionVariable getQuestionVariable() {
        return questionVariable;
    }
}
