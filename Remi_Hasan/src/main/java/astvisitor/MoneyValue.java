package astvisitor;

import java.math.BigDecimal;

class MoneyValue extends NumValue<BigDecimal>{
    MoneyValue(BigDecimal value) {
        super(value);
    }
}
