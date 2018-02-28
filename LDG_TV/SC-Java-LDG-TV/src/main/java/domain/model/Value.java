package domain.model;

public class Value extends QuestionVariableValue{
    private String value;

    public Value(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
