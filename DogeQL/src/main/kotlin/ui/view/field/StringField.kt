package ui.view.field

import tornadofx.bind
import tornadofx.stripWhitespace
import ui.model.StringViewModel

class StringField(question : StringViewModel) : QuestionTextField(question){
    init {
        with(root){
            stripWhitespace()
            bind(question.stringProperty)
            attachListener(textProperty())
        }
    }
}