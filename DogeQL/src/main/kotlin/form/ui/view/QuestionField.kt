package form.ui.view

import form.data.question.SymbolType
import form.ui.model.QuestionModel
import javafx.scene.layout.HBox
import tornadofx.add

class QuestionField(question: QuestionModel) : HBox() {

    init {
        when (question.item.value.type) {
            SymbolType.BOOLEAN -> add(QuestionCheckBox(question))
            else -> add(QuestionTextField(question))
        }
        isDisable = question.readOnly
    }
}