package data.question

import data.symbol.SymbolTable
import data.value.BaseSymbolValue

data class Question(val name: String, val label: String, var value: BaseSymbolValue) {
    fun update(symbolTable: SymbolTable) {
        symbolTable.findSymbol(name)?.let {
            value = it.value
        } ?: run {
            throw IllegalStateException("TODO")
        }
    }
}