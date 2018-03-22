package data.value

import common.Color
import data.question.SymbolType


class ColorValue(var value: Color) : BaseSymbolValue(SymbolType.COLOR) {

    constructor(value: String) : this(Color.fromString(value))

    override fun valueString(): String {
        return value.toString()
    }

}