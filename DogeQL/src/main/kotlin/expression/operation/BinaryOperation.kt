package expression.operation

import data.value.BaseSymbolValue
import data.value.BooleanValue

enum class BinaryOperation(private val operation: (left: BaseSymbolValue, right: BaseSymbolValue) -> BaseSymbolValue) {

    Greater({ left, right ->
        BooleanValue(left > right)
    }),

    Less({ left, right ->
        BooleanValue(left < right)
    }),

    GreaterOrEqual({ left, right ->
        BooleanValue(left >= right)
    }),

    LessOrEqual({ left, right ->
        BooleanValue(left <= right)
    }),

    Equal({ left, right ->
        BooleanValue(left == right)
    }),

    NotEqual({ left, right ->
        BooleanValue(left != right)
    }),

    And({ left, right ->
        val boolTrue = BooleanValue(true)
        Equal(left, boolTrue) and Equal(right, boolTrue)
    }),

    Or({ left, right ->
        val boolTrue = BooleanValue(true)
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
    });

    operator fun invoke(left: BaseSymbolValue, right: BaseSymbolValue): BaseSymbolValue {
        return operation(left, right)
    }

}
