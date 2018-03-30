package ui.controller

import doge.ast.DogeParser
import doge.ast.node.QLNode
import ui.model.domain.Question
import doge.data.symbol.SymbolTable
import ui.visitor.QuestionVisitor
import doge.visitor.ValueUpdateVisitor
import qls.ast.QlsParser
import qls.ast.node.QlsNode
import tornadofx.Controller
import tornadofx.observable
import java.io.File

class DogeController : Controller() {

    private var symbolTable: SymbolTable? = null
    private var ast: QLNode? = null

    var questions = mutableListOf<Question>().observable()
    var style: QlsNode? = null

    fun loadQuestionnaire(file: File) {
        val parseResult = DogeParser().parse(file)

        parseResult?.let {
            symbolTable = it.symbolTable
            ast = it.ast
        }

        reload()
    }

    fun loadStyle(file: File) {
        style = QlsParser().parse(file)
    }

    fun reload() {
        symbolTable.let {
            val visitor = QuestionVisitor(symbolTable!!)
            val enabledQuestions = ast!!.accept(visitor)
            updateQuestions(enabledQuestions)
        }
    }

    fun evaluate(question: Question) {
        symbolTable?.let {
            it.assign(question.name, question.value)
            ast?.accept(ValueUpdateVisitor.default(it))
        }
    }

    // Replacing observable list will break observable
    // That is why we update internal values
    private fun updateQuestions(newDataQuestions: List<Question>) {

        val toAdd = newDataQuestions.filter { question ->
            question !in questions || question.readOnly
        }

        questions.removeIf { question ->
            question !in newDataQuestions || question.readOnly
        }

        toAdd.forEach { question ->
            questions.add(newDataQuestions.indexOf(question), question)
        }

    }

}