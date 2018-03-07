package astvisitor;

import java.math.BigDecimal;

class MoneyValue extends Value<BigDecimal>{
    final BigDecimal value;
    MoneyValue(Number value){
        this.value = new BigDecimal(value.toString());
    }
}
