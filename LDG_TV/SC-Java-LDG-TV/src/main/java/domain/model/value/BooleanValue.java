package domain.model.value;

public class BooleanValue extends Value{
    private Boolean value;

    public BooleanValue(String value){
        this.value = Boolean.valueOf(value);
    }
    public Boolean getValue() {
        return value;
    }
}
