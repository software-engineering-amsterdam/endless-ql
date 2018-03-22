package data.value

import data.question.SymbolType
import java.time.LocalDate

class DateValue(var value: LocalDate) : BaseSymbolValue(SymbolType.DATE) {

    companion object {
        fun fromString(string: String): DateValue {
            val date = LocalDate.parse(string)
            return DateValue(date)
        }
    }

    override fun compareTo(other: BaseSymbolValue): Int = when (other) {
        is DateValue -> value.compareTo(other.value)
        else -> super.compareTo(other)
    }

    override fun valueString(): String {
        return value.toString()
    }

}