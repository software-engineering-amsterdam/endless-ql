package expression

import data.BaseSymbolValue

enum class UnaryOperation(internal val operation: (input: BaseSymbolValue) -> BaseSymbolValue) {

    Negate({ input ->
        !input
    })

}

operator fun UnaryOperation.invoke(input: BaseSymbolValue): BaseSymbolValue {
    return operation.invoke(input)
}