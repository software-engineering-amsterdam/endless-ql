package ui.controller

import doge.ast.DogeParser
import doge.ast.location.SourceLocation
import doge.data.question.Question
import doge.data.value.StringValue
import doge.visitor.UiVisitor
import qls.model.StyleSheet
import tornadofx.Controller
import tornadofx.observable

class DogeController : Controller() {

    var questions = mutableListOf<Question>().observable()

    fun load(){
        val ast = DogeParser().parse()
        val enabledQuestions = UiVisitor().visit(ast)

        questions.addAll(enabledQuestions)
    }

    fun getStyle(): StyleSheet {
        val ast = DogeParser().parse() as StyleSheet
        return ast
    }

    fun test(){
        questions.add(Question("", "asd", StringValue("sdff"), SourceLocation(0,0,0,0), SourceLocation(0,0,0,0), true))
        questions.first().value.booleanValue.value = true
    }

    private fun updateViewModel(newDataQuestions: List<Question>) {
//        val modelFactory = ViewModelFactory()
//
//        val toAdd = newDataQuestions.filter { question ->
//            question !in dataQuestions || question.readOnly
//        }

//        questions.removeIf { question ->
//            question.item !in newDataQuestions || question.item.readOnly
//        }

        // Only add new questions, leave old questions as is
//        toAdd.forEach { question ->
//            questions.add(newDataQuestions.indexOf(question), modelFactory.createUiQuestionModel(question))
//        }

    }

}