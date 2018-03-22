package form.data.value

import form.common.Color
import form.data.question.SymbolType


class ColorValue(var value: Color) : BaseSymbolValue(SymbolType.COLOR) {

    constructor(value: String) : this(Color.fromString(value))

    override fun valueString(): String = value.toString()

}