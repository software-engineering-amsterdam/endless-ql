package data.value

import data.question.QuestionType
import java.math.BigDecimal

class IntegerValue(var value: Int) : BaseSymbolValue(QuestionType.INTEGER) {

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value + that.value)
        else -> super.plus(that)
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value - that.value)
        else -> super.minus(that)
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value * that.value)
        else -> super.times(that)
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is IntegerValue -> IntegerValue(value / that.value)
        else -> super.div(that)
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is IntegerValue -> value == other.value
        else -> super.equals(other)
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is IntegerValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

    override fun castTo(that: QuestionType): BaseSymbolValue? = when (that) {
        QuestionType.DECIMAL -> DecimalValue(BigDecimal(value))
        QuestionType.BOOLEAN -> BooleanValue(value != 0)
        else -> super.castTo(that)
    }

    override fun valueString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return value
    }

}
