package ui.model

import doge.data.question.Question
import tornadofx.ItemViewModel
import ui.controller.DogeController

abstract class QuestionViewModel(question: Question) : ItemViewModel<Question>(question) {

    private val dogeController: DogeController by inject()

    val readOnly = question.readOnly


    fun update() {
        // Only update if there are changes
        // Added this check to remove unnecessary updates
        if (dirtyProperties.size > 0 && dirtyProperties.first() != null) {
            synchronizeDataModel()
//            dogeController.updateQuestion(item)
            load()
        }
    }

    fun load() {
        dogeController.load()
    }

    abstract fun setViewModelValue(question: Question)

    abstract fun synchronizeDataModel()
}


