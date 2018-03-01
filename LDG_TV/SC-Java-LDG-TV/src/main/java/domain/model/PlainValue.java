package domain.model;

import domain.model.question.QuestionVariableValue;

public class PlainValue extends QuestionVariableValue {
    private String value;

    public PlainValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
