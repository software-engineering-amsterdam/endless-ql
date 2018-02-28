package data

import javax.naming.OperationNotSupportedException

abstract class BaseSymbolValue(var type: QuestionType) : Comparable<BaseSymbolValue> {

    open infix fun and(that: BaseSymbolValue): BooleanValue {
        unsupportedOperation("&&", that)
    }

    open infix fun or(that: BaseSymbolValue): BooleanValue {
        unsupportedOperation("||", that)
    }

    open operator fun plus(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("+", that)
    }

    open operator fun minus(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("-", that)
    }

    open operator fun times(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("*", that)
    }

    open operator fun div(that: BaseSymbolValue): BaseSymbolValue {
        unsupportedOperation("/", that)
    }

    open operator fun not(): BaseSymbolValue {
        unsupportedOperation("!")
    }

    override fun compareTo(other: BaseSymbolValue): Int {
        unsupportedOperation("compareTo", other)
    }

    infix fun ofSameType(that: BaseSymbolValue): Boolean {
        return this.type == that.type
    }

    val integerValue: IntegerValue
        get() = this as IntegerValue

    val booleanValue: BooleanValue
        get() = this as BooleanValue

    internal fun unsupportedOperation(operator: String, that: BaseSymbolValue? = null): Nothing {
        that?.let {
            throw OperationNotSupportedException("Unable to apply '$operator' to  $type and ${that.type}")
        } ?: run {
            throw OperationNotSupportedException("Unable to apply '$operator' to  $type")
        }
    }

    abstract fun valueString(): String

}
