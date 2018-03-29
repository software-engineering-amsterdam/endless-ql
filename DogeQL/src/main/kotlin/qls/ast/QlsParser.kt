package qls.ast

import org.antlr.v4.runtime.ANTLRInputStream
import QuestionnaireLanguageStyleGrammarLexer
import org.antlr.v4.runtime.CommonTokenStream
import QuestionnaireLanguageStyleGrammarParser


class QlsParser(){
    fun parse() {
        val fileStream = javaClass.getResource("/sample/Style.shiba").openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionnaireLanguageStyleGrammarLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionnaireLanguageStyleGrammarParser(tokens)

        val visitor = QuestionnaireLanguageStyleVisitor()

        val ast = visitor.visit(parser.stylesheet())

        return TODO()
    }

}