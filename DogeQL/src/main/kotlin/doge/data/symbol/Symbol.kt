package doge.data.symbol

import doge.data.question.SymbolType
import doge.data.value.BaseSymbolValue
import doge.expression.Expression

class Symbol(type: SymbolType, val expression: Expression?) {

    var value: BaseSymbolValue = type.getDefaultInstance()

    fun evaluate(symbolTable: SymbolTable) {
        expression?.let {
            value = expression.evaluate(symbolTable)
        }
    }

    fun assign(value: BaseSymbolValue) {
        this.value = value
    }

}