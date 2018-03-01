package domain.model;

public class Question {
    private String label;
    private String variable;
    private String variableType;
    private QuestionVariableValue variableValue;

    public Question(String label, String variable, String variableType, QuestionVariableValue variableValue){
        this.label = label;
        this.variable = variable;
        this.variableType = variableType;
        this.variableValue = variableValue;
    }

    public String getLabel() {
        return label;
    }

    public String getVariable() {
        return variable;
    }

    public String getVariableType() {
        return variableType;
    }

    public QuestionVariableValue getVariableValue() {
        return variableValue;
    }
}
