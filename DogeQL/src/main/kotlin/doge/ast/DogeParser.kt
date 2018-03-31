package doge.ast

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import doge.ast.node.QLNode
import doge.data.symbol.SymbolTable
import doge.typechecker.TypeChecker
import doge.visitor.ValueUpdateVisitor
import jdk.nashorn.internal.runtime.Undefined
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import qls.ast.node.QlsNode
import java.io.File
import java.io.FileInputStream


data class DogeParseResult(val ast: QLNode?, val symbolTable: SymbolTable?, val info : List<String>)

class DogeParser {

    fun parse(file: File): DogeParseResult? {
        val fileName = file.path

        var errors: MutableList<String>

        FileInputStream(fileName).use {
            val stream = ANTLRInputStream(it)
            val lexer = QuestionnaireLanguageGrammarLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val parser = QuestionnaireLanguageGrammarParser(tokens)
            val visitor = QuestionnaireLanguageVisitor()
            val ast = visitor.visit(parser.form())

            val symbolTable = SymbolTable()
            errors = TypeChecker(fileName, symbolTable, ast).check()


            if (errors.isEmpty()) {
                ValueUpdateVisitor.default(symbolTable).visit(ast)

            }

            return DogeParseResult(ast, symbolTable, errors)

        }
    }

}