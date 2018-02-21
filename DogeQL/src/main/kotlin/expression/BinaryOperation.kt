package expression

import data.BaseSymbolValue

enum class BinaryOperation(internal val operation: (left: BaseSymbolValue, right: BaseSymbolValue) -> BaseSymbolValue) {

    Greater({ left, right ->
        data.BooleanValue(left > right)
    }),

    Less({ left, right ->
        data.BooleanValue(left < right)
    }),

    GreaterOrEqual({ left, right ->
        data.BooleanValue(left >= right)
    }),

    LessOrEqual({ left, right ->
        data.BooleanValue(left <= right)
    }),

    Equal({ left, right ->
        data.BooleanValue(left == right)
    }),

    NotEqual({ left, right ->
        data.BooleanValue(left != right)
    }),

    And({ left, right ->
        val boolTrue = data.BooleanValue(true)
        Equal(left, boolTrue) and Equal(right, boolTrue)
    }),

    Or({ left, right ->
        val boolTrue = data.BooleanValue(true)
        Equal(left, boolTrue) or (Equal(right, boolTrue))
    }),

    Add({ left, right ->
        left + right
    }),

    Subtract({ left, right ->
        left - right
    }),

    Multiply({ left, right ->
        left * right
    }),

    Divide({ left, right ->
        left / right
    })

}

operator fun BinaryOperation.invoke(left: BaseSymbolValue, right: BaseSymbolValue): BaseSymbolValue {
    return operation.invoke(left, right)
}