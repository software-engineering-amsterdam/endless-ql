package data

class BooleanValue(var value: Boolean) : BaseSymbolValue(QuestionType.BOOLEAN) {

    override fun and(that: BaseSymbolValue): BooleanValue {
        return when (that) {
            is BooleanValue -> BooleanValue(value && that.value)
            else -> unsupportedOperation("and", that)
        }
    }

    override fun or(that: BaseSymbolValue): BooleanValue {
        return when (that) {
            is BooleanValue -> BooleanValue(value || that.value)
            else -> unsupportedOperation("||", that)
        }
    }

    override fun plus(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("+", that)
    }

    override fun minus(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("-", that)
    }

    override fun times(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("*", that)
    }

    override fun div(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("/", that)
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is BooleanValue -> value == other.value
            else -> super.equals(other)
        }
    }

    override fun compareTo(other: BaseSymbolValue): Int {
        return when (other) {
            is BooleanValue -> value.compareTo(other.value)
            else -> unsupportedOperation("compareTo", other)
        }
    }

}
