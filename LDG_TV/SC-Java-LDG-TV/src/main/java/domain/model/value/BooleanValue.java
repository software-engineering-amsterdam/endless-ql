package domain.model.value;

public class BooleanValue extends Value<Boolean>{
    private Boolean value;

    public BooleanValue(String value){
        this.value = Boolean.valueOf(value);
    }
    @Override
    public Boolean getValue() {
        return value;
    }
}
