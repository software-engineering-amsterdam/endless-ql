package data

class IntegerValue(val value: Int) : BaseSymbolValue(QuestionType.INTEGER) {

    override fun and(that: BaseSymbolValue): BooleanValue {
        unsupportedOperation("and", that)
    }

    override fun or(that: BaseSymbolValue): BooleanValue {
        unsupportedOperation("or", that)
    }

    override fun plus(that: BaseSymbolValue): BaseSymbolValue {
        return when (that) {
            is IntegerValue -> IntegerValue(value + that.value)
            else -> unsupportedOperation("+", that)
        }
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue {
        return when (that) {
            is IntegerValue -> IntegerValue(value - that.value)
            else -> unsupportedOperation("-", that)
        }
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue {
        return when (that) {
            is IntegerValue -> IntegerValue(value * that.value)
            else -> unsupportedOperation("*", that)
        }
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue {
        return when (that) {
            is IntegerValue -> IntegerValue(value / that.value)
            else -> unsupportedOperation("/", that)
        }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is IntegerValue -> value == other.value
            else -> super.equals(other)
        }
    }

    override fun compareTo(other: BaseSymbolValue): Int {
        return when (other) {
            is IntegerValue -> value.compareTo(other.value)
            else -> unsupportedOperation("compareTo", other)
        }
    }

}
