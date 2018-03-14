package ui.view

import data.question.QuestionType
import javafx.util.converter.BigDecimalStringConverter
import tornadofx.*
import ui.model.QuestionModel

class QuestionField(question: QuestionModel) : View(){

    override val root = field ()

    init{
        with(root){
            when(question.item.value.type) {
                QuestionType.INTEGER ->textfield(question.integerValue).stripNonInteger()
                QuestionType.STRING -> textfield(question.stringValue)
                QuestionType.DECIMAL-> textfield(question.decimalValue, BigDecimalStringConverter()).stripNonNumeric()
                QuestionType.MONEY-> textfield(question.moneyValue, BigDecimalStringConverter()).stripNonNumeric()
                QuestionType.BOOLEAN-> checkbox().bind(question.booleanValue)
                else -> throw IllegalArgumentException("unsupported type")
            }
        }
    }

}