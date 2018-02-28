package data

class IntegerValue(val value: Int) : BaseSymbolValue(QuestionType.INTEGER) {

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

    override fun valueString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return value
    }

}
