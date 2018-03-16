package ui.controller

import ast.DogeParser
import com.sun.org.apache.xerces.internal.util.SymbolTable
import data.question.Question
import data.question.QuestionTable
import data.question.SymbolType
import tornadofx.Controller

class DogeController: Controller() {

    private val tree = DogeParser().parse()

//    private val symbolTable : SymbolTable()

    fun getQuestions(): List<Question> {
        tree.symbolTable.evaluateTable()
        return tree.getEnabledQuestions()
    }

    fun updateQuestion(question: Question){
        tree.updateQuestion(question)
    }
}