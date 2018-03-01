package domain.model.question;

import domain.model.Condition;

public class QuestionVariable extends Condition{
    private String label;
    private QuestionVariableType questionVariableType;
    private QuestionVariableValue questionVariableValue;

    public QuestionVariable(String label, QuestionVariableType questionVariableType, QuestionVariableValue questionVariableValue) {
        this.label = label;
        this.questionVariableType = questionVariableType;
        this.questionVariableValue = questionVariableValue;
    }

    public String getLabel() {
        return label;
    }

    public QuestionVariableType getQuestionVariableType() {
        return questionVariableType;
    }

    public QuestionVariableValue getQuestionVariableValue() {
        return questionVariableValue;
    }
}
