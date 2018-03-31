package ui.model

import ui.model.domain.Question
import doge.data.question.SymbolType

class ViewModelFactory {

    fun createQuestionViewModel(question : Question) : QuestionViewModel {

        return when(question.value.type){
            SymbolType.BOOLEAN -> BooleanViewModel(question)
            SymbolType.INTEGER -> IntegerViewModel(question)
            SymbolType.DECIMAL -> DecimalViewModel(question)
            SymbolType.STRING -> StringViewModel(question)
            SymbolType.MONEY -> MoneyViewModel(question)
            SymbolType.DATE -> DateViewModel(question)
            else -> throw IllegalArgumentException("${question.value.type} unsupported type")
        }
    }
}