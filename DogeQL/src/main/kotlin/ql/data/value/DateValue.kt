package ql.data.value

import ql.data.question.SymbolType
import java.time.LocalDate

class DateValue(var value: LocalDate) : BaseSymbolValue(SymbolType.DATE) {

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is DateValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

}