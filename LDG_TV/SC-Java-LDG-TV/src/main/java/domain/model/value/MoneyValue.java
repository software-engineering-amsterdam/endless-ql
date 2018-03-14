package domain.model.value;

public class MoneyValue extends Value<Integer>{
    private Integer value;

    public MoneyValue(Integer value){
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public void accept(Integer integer) throws Exception {
        this.value = integer;
    }


}
