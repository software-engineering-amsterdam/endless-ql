package domain.model.value;

public class MoneyValue extends Value<Integer> {
    private Integer value;

    public MoneyValue(Integer value){
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
    @Override
    public Integer setValue(Object o) {
        return this.value = (Integer) o;
    }
}
