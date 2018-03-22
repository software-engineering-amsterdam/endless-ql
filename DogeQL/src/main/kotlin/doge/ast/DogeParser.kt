package doge.ast

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import doge.node.Node
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream


class DogeParser {

    fun parse(): Node {
        val fileStream = javaClass.getResource("/sample/TestQuestionare.doge").openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionnaireLanguageGrammarLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionnaireLanguageGrammarParser(tokens)

        val visitor = QuestionnaireLanguageVisitor()

        visitor.visit(parser.form())

//        val listener = DogeListener()

//        walker.walk(listener, parser.form())

//        val tree = listener.getParsedDogeLanguage()

//        val result = TypeChecker(listener.symbolTable).check(tree)

//        if (result.hasErrors()) {
//            result.printErrors()
//
//            throw Exception() // TODO: fix this flow
//        }

        return TODO()
    }

}