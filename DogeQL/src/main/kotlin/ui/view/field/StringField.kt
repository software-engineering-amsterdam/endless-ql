package ui.view.field

import ui.model.StringViewModel
import tornadofx.bind
import tornadofx.stripWhitespace
import ui.view.field.QuestionTextField

class StringField(question : StringViewModel) : QuestionTextField(question){
    init {
        with(root){
            stripWhitespace()
            bind(question.stringProperty)
            attachListener(textProperty())
        }
    }
}