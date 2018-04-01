package ui.model

import ui.model.domain.Question
import tornadofx.ItemViewModel
import ui.controller.DogeController

abstract class QuestionViewModel(question: Question) : ItemViewModel<Question>(question) {

    private val dogeController: DogeController by inject()

    val readOnly = question.readOnly

    fun update() {
        // Only update if there are changes
        // Added this check to remove unnecessary updates
        if (dirtyProperties.size > 0 && dirtyProperties.first().value != null) {
            synchronizeDataModel()
            dogeController.evaluate(item)
            dogeController.reloadQuestions()
        }
    }

    abstract fun setViewModelValue(question: Question)

    // We synchronize manually, because our domain model is not using TornadoFx properties
    // We also don't want to call commit, because now we can update the view after every change
    abstract fun synchronizeDataModel()
}


