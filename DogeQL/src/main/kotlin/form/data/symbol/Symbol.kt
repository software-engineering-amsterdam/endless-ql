package form.data.symbol

import form.data.question.SymbolType
import form.data.value.BaseSymbolValue
import form.expression.Expression

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