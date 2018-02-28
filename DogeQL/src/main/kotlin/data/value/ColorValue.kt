package data.value

import common.Color
import data.question.QuestionType


class ColorValue(var value: Color): BaseSymbolValue(QuestionType.COLOR)  {

    override fun valueString(): String {
        return value.toString()
    }

}