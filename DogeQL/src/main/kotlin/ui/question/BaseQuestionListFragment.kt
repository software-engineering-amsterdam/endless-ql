package ui.question

import data.Question
import tornadofx.*

abstract class BaseQuestionListFragment: ListCellFragment<Question>() {

    val questionItemViewModel: QuestionItemViewModel by inject()

    override val root = form {

        fieldset {

            field("Question") {
                label {
                    
                }
            }

        }

    }

}