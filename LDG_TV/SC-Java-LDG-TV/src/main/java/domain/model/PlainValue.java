package domain.model;

public class PlainValue extends QuestionVariableValue{
    private String value;

    public PlainValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
