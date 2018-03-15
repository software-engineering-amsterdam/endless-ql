package data.symbol

import common.Color
import data.question.SymbolType
import data.value.*
import expression.Expression
import java.time.LocalDate

class Symbol(type: SymbolType, val expression: Expression?) {

    var value: BaseSymbolValue = when (type) {
        SymbolType.UNDEFINED -> throw IllegalStateException("Unable to create symbol of undecided type")
        SymbolType.Boolean -> BooleanValue(false)
        SymbolType.INTEGER -> IntegerValue(0)
        SymbolType.DECIMAL -> DecimalValue(0)
        SymbolType.STRING -> StringValue("")
        SymbolType.MONEY -> MoneyValue(0)
        SymbolType.COLOR -> ColorValue(Color(0, 0, 0, 0))
        SymbolType.DATE -> DateValue(LocalDate.now())
    }

    fun evaluate(symbolTable: SymbolTable) {
        expression?.let {
            value = expression.evaluate(symbolTable)
        }
    }

    fun assign(value: BaseSymbolValue) {
        if (this.value.type != value.type) {
            throw IllegalArgumentException("Unable to assign type ${value.type} to ${this.value.type}")
        }

        this.value = value
    }

}