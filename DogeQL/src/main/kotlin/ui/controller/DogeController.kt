package ui.controller

import doge.ast.DogeParser
import doge.ast.location.SourceLocation
import doge.ast.node.QLNode
import doge.data.question.Question
import doge.data.value.StringValue
import doge.visitor.UiVisitor
import qls.ast.QlsParser
import qls.ast.node.QlsNode
import tornadofx.Controller
import tornadofx.observable

class DogeController : Controller() {

    var questions = mutableListOf<Question>().observable()

    fun load(){
        questions.addAll(getQuestions(DogeParser().parse()).observable())
    }

    init {
        val qlAST = DogeParser().parse()
        val qlsAST = QlsParser().parse()

        //Build UI model
        var questions = getQuestions(DogeParser().parse())
        var style = getStyle(qlsAST)

        // Render view
//        Renderer().render(questions, style)
    }

    fun getQuestions(ast : QLNode): List<Question> {
       return UiVisitor().visit(ast)

    }

    fun getStyle(ast : QlsNode){
//        val style = StyleVisitor().visit(ast)
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