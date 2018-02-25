package domain.model;

public class Question {
    private String label;
    private String variable;
    private String variableType;
    private Variable variableValue;

    public Question(String label, String variable, String variableType, Variable variableValue){
        this.label = label;
        this.variable = variable;
        this.variableType = variableType;
        this.variableValue = variableValue;
    }
}
