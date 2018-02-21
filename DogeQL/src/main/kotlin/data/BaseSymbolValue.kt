package data

import javax.naming.OperationNotSupportedException

abstract class BaseSymbolValue(var type: QuestionType): Comparable<BaseSymbolValue> {

    abstract infix fun and(that: BaseSymbolValue): BooleanValue
    abstract infix fun or(that: BaseSymbolValue): BooleanValue
    abstract operator fun plus(that: BaseSymbolValue): BaseSymbolValue
    abstract operator fun minus(that: BaseSymbolValue): BaseSymbolValue
    abstract operator fun times(that: BaseSymbolValue): BaseSymbolValue
    abstract operator fun div(that: BaseSymbolValue): BaseSymbolValue

    val integerValue: IntegerValue
        get() = this as IntegerValue

    val booleanValue: BooleanValue
        get() = this as BooleanValue

    internal fun unsupportedOperation(operator: String, that: BaseSymbolValue): Nothing {
        throw OperationNotSupportedException("Unable to apply '$operator' to  $type and ${that.type}")
    }

}
