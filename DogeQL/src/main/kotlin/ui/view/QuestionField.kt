package ui.view

import data.question.SymbolType
import javafx.scene.layout.HBox
import tornadofx.add
import ui.model.QuestionModel

class QuestionField(question: QuestionModel) : HBox() {

    init {
        when (question.item.value.type) {
            SymbolType.BOOLEAN -> add(QuestionCheckBox(question))
            else -> add(QuestionTextField(question))
        }
    }
}