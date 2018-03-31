package ui.model.domain

import doge.data.value.BaseSymbolValue

data class Question(
        val name: String,
        val label: String,
        val value: BaseSymbolValue,
        val readOnly: Boolean
)