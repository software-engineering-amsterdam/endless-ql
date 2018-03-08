package data.value

import data.question.QuestionType

class StringValue(var value: String): BaseSymbolValue(QuestionType.STRING) {

    override fun plus(that: BaseSymbolValue): BaseSymbolValue = when (that) {
        is StringValue -> StringValue(value + that.value)
        else -> super.plus(that)
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is StringValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

    override fun valueString(): String {
        return value
    }
}