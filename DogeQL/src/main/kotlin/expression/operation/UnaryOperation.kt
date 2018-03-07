package expression.operation

import data.value.BaseSymbolValue

enum class UnaryOperation(private val operation: (input: BaseSymbolValue) -> BaseSymbolValue) {

    Negate({ input ->
        !input
    });

    operator fun invoke(input: BaseSymbolValue): BaseSymbolValue {
        return operation(input)
    }

}

