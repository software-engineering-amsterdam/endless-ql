package ui.model.domain

import doge.common.Name
import doge.data.value.BaseSymbolValue

data class Question(
        val name: Name,
        val label: String,
        val value: BaseSymbolValue,
        val readOnly: Boolean,
        val visible : Boolean
)