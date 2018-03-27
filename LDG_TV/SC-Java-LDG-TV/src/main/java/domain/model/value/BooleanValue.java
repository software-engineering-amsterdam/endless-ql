package domain.model.value;

public class BooleanValue implements Value<Boolean>{
    private Boolean value;

    public BooleanValue(Boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Boolean setValue(Object o) {
        return this.value = (Boolean) o;
    }
}
