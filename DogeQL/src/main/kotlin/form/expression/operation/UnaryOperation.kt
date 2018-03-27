package form.expression.operation

import form.data.question.SymbolType
import form.data.value.BaseSymbolValue
import javax.naming.OperationNotSupportedException

enum class UnaryOperation(private val operation: (input: BaseSymbolValue) -> BaseSymbolValue) {

    NEGATE({ input ->
        !input
    });

    operator fun invoke(input: BaseSymbolValue): BaseSymbolValue {
        return operation(input)
    }

    fun getResolveType(type: SymbolType): SymbolType {
        val defaultValue = type.getDefaultInstance()

        return try {
            this(defaultValue).type
        } catch (_: OperationNotSupportedException) {
            SymbolType.UNDEFINED
        }
    }

}

