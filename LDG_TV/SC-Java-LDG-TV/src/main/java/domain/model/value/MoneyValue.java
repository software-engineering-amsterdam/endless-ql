package domain.model.value;

public class MoneyValue implements Value<Integer> {
    private Integer value;

    public MoneyValue(Integer value){
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
    @Override
    public Integer setValue(Integer value) {
        return this.value = value;
    }
}
