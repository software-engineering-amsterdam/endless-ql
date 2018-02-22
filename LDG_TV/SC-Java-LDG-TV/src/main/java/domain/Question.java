package domain;

public class Question {
    private String label;
    private String variable;
    private String variableType;
    private String variableValue;
    private String variableValueType;

    public Question(String label, String variable, String variableType){
        this.label = label;
        this.variable = variable;
        this.variableType = variableType;
    }
}
