package ui.question

import data.Question
import tornadofx.*

class ListViewItem(val question: Question) : View(){

    override val root = form {
        form {
            fieldset {
                field(question.label) {
                    textfield()
                }
            }
        }
    }
}

