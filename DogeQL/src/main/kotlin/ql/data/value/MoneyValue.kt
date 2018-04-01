package ql.data.value

import ql.data.question.SymbolType
import java.math.BigDecimal

class MoneyValue(value: BigDecimal) : DecimalValue(value) {

    constructor(value: String) : this(BigDecimal(value))
    constructor(value: Int) : this(BigDecimal(value))

    override fun castTo(that: SymbolType) = when (that) {
        SymbolType.DECIMAL -> DecimalValue(value)
        else -> super.castTo(that)
    }

}