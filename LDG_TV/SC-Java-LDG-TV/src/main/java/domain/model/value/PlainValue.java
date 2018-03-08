package domain.model.value;

public class PlainValue extends Value<String>{
    private String value;

    public PlainValue(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
