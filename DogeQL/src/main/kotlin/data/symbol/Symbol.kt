package data.symbol

import data.question.SymbolType
import data.value.BaseSymbolValue
import expression.Expression

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