package data.value

import data.question.QuestionType
import javax.naming.OperationNotSupportedException

abstract class BaseSymbolValue(val type: QuestionType) : Comparable<BaseSymbolValue> {

    abstract fun valueString(): String

    open infix fun and(that: BaseSymbolValue): BooleanValue {
        return attemptOperator(that, "&&", { it and that })
    }

    open infix fun or(that: BaseSymbolValue): BooleanValue {
        return attemptOperator(that, "||", { it or that })
    }

    open operator fun plus(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "+", { it + that })
    }

    open operator fun minus(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "-", { it - that })
    }

    open operator fun times(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "*", { it * that })
    }

    open operator fun div(that: BaseSymbolValue): BaseSymbolValue {
        return attemptOperator(that, "/", { it / that })
    }

    open operator fun not(): BaseSymbolValue {
        unsupportedOperation("!")
    }

    override fun compareTo(other: BaseSymbolValue): Int {
        return attemptOperator(other, "compareTo", { it.compareTo(other) })
    }

    open fun castTo(that: QuestionType): BaseSymbolValue? = when (that) {
        type -> this
        else -> null
    }

    val integerValue: IntegerValue
        get() = this as IntegerValue

    val booleanValue: BooleanValue
        get() = this as BooleanValue

    val decimalValue: DecimalValue
        get() = this as DecimalValue

    val stringValue: StringValue
        get() = this as StringValue

    val moneyValue: MoneyValue
        get() = this as MoneyValue

    private fun unsupportedOperation(operator: String, that: BaseSymbolValue? = null): Nothing {
        that?.let {
            throw OperationNotSupportedException("Unable to apply '$operator' to  $type and ${that.type}")
        } ?: run {
            throw OperationNotSupportedException("Unable to apply '$operator' to  $type")
        }
    }

    private inline fun <O> attemptOperator(
            that: BaseSymbolValue, operatorString: String, operator: (BaseSymbolValue) -> O
    ): O {
        if (type == that.type) {
            unsupportedOperation(operatorString, that)
        }

        return castAndRetry(that, operatorString, operator)
    }

    private inline fun <O> castAndRetry(
            that: BaseSymbolValue, operatorString: String, operator: (BaseSymbolValue) -> O
    ): O {
        val castedThis = castTo(that.type)

        return when (castedThis) {
            null -> unsupportedOperation(operatorString, that)
            else -> operator(castedThis)
        }
    }

}
