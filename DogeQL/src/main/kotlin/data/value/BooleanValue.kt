package data.value

import data.question.SymbolType
import java.math.BigDecimal

class BooleanValue(var value: Boolean) : BaseSymbolValue(SymbolType.Boolean) {

    constructor(value: String) : this(value.toBoolean())

    override fun and(that: BaseSymbolValue): BooleanValue = when (that) {
        is BooleanValue -> BooleanValue(value && that.value)
        else -> super.and(that)
    }

    override fun or(that: BaseSymbolValue): BooleanValue = when (that) {
        is BooleanValue -> BooleanValue(value || that.value)
        else -> super.or(that)
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is BooleanValue -> value == other.value
        else -> super.equals(other)
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is BooleanValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

    override fun not(): BaseSymbolValue {
        return BooleanValue(!value)
    }

    override fun castTo(that: SymbolType): BaseSymbolValue? = when (that) {
        SymbolType.Integer -> IntegerValue(if (value) 1 else 0)
        SymbolType.Decimal -> DecimalValue(if (value) BigDecimal("1") else BigDecimal("0"))
        else -> super.castTo(that)
    }

    override fun valueString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
