package data

import java.math.BigDecimal

class DecimalValue(private val value: BigDecimal) : BaseSymbolValue(QuestionType.DECIMAL) {

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value + that.value)
        else -> super.plus(that)
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value - that.value)
        else -> super.minus(that)
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value * that.value)
        else -> super.times(that)
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is DecimalValue -> DecimalValue(value / that.value)
        else -> super.div(that)
    }

    override fun valueString(): String {
        return value.toString()
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is DecimalValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

}