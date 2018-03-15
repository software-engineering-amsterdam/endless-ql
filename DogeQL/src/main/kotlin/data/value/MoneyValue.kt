package data.value

import data.question.SymbolType
import java.math.BigDecimal

class MoneyValue(var value: BigDecimal) : BaseSymbolValue(SymbolType.MONEY) {

    constructor(value: String) : this(BigDecimal(value))
    constructor(value: Int) : this(BigDecimal(value))

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is MoneyValue -> MoneyValue(value + that.value)
        else -> super.plus(that)
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is MoneyValue -> MoneyValue(value - that.value)
        else -> super.minus(that)
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is MoneyValue -> MoneyValue(value * that.value)
        else -> super.times(that)
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is MoneyValue -> MoneyValue(value / that.value)
        else -> super.div(that)
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is MoneyValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

    override fun valueString(): String {
        return "Ð$value"
    }

}