package doge.ast

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import doge.ast.node.QLNode
import doge.data.symbol.SymbolTable
import doge.typechecker.TypeChecker
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream


data class DogeParseResult(val ast: QLNode, val symbolTable: SymbolTable)

class DogeParser {

    fun parse(): DogeParseResult? {
        val fileName = "/sample/TestQuestionare.doge"

        javaClass.getResource(fileName).openStream().use {
            val stream = ANTLRInputStream(it)
            val lexer = QuestionnaireLanguageGrammarLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val parser = QuestionnaireLanguageGrammarParser(tokens)
            val visitor = QuestionnaireLanguageVisitor()
            val ast = visitor.visit(parser.form())

            val symbolTable = SymbolTable()
            val validQL = TypeChecker(fileName, symbolTable, ast).check()

            if (validQL) {
                return DogeParseResult(ast, symbolTable)
            }
        }

        return null
    }

}