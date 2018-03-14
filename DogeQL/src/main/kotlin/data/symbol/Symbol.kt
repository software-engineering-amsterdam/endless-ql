package data.symbol

import common.Color
import data.question.SymbolType
import data.value.*
import expression.Expression
import javax.naming.OperationNotSupportedException

class Symbol(type: SymbolType, val expression: Expression?) {

    var value: BaseSymbolValue = when (type) {
        SymbolType.Boolean -> BooleanValue(false)
        SymbolType.Integer -> IntegerValue(0)
        SymbolType.Decimal -> DecimalValue(0)
        SymbolType.String -> StringValue("")
        SymbolType.Money -> MoneyValue(0)
        SymbolType.Color -> ColorValue(Color(0, 0, 0, 0))
        SymbolType.Undecided -> throw IllegalStateException("Unable to create symbol of undecided type")
    }

    fun evaluate(symbolTable: SymbolTable) {
        expression?.let {
            value = expression.evaluate(symbolTable)
        } ?: run {
            throw OperationNotSupportedException("Unable to evaluate variable")
        }
    }

    fun assign(value: BaseSymbolValue) {
        if (this.value.type != value.type) {
            throw IllegalArgumentException("Unable to assign type ${value.type} to ${this.value.type}")
        }

        this.value = value
    }

}