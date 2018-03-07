package domain.model.variable;

public class PlainValue extends Value{
    private String value;

    public PlainValue(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
