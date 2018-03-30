package doge.data.question

import doge.ast.location.SourceLocation
import doge.data.value.BaseSymbolValue

data class Question(
        val name: String,
        val label: String,
        var value: BaseSymbolValue,
        val nameLocation: SourceLocation,
        val labelLocation: SourceLocation,
        val readOnly: Boolean
)