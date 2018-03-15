package classes.types;

public class MoneyValue extends Value<Double> {
    MoneyValue(Double value, String type) {
        super(value);
        setType(Value.MONEY);
    }
}
