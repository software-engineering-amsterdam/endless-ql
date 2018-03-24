package form.data.question

import form.data.symbol.SymbolTable
import form.data.value.BaseSymbolValue
import form.expression.SourceLocation

data class Question(
        val name: String,
        val label: String,
        var value: BaseSymbolValue,
        val nameLocation: SourceLocation,
        val labelLocation: SourceLocation,
        val readOnly: Boolean
) {
    fun update(symbolTable: SymbolTable) {
        symbolTable.findSymbol(name)?.let {
            value = it.value
        } ?: run {
            throw IllegalStateException("TODO")
        }
    }
}