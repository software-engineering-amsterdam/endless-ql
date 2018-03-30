package qls.ast

import QuestionnaireLanguageStyleGrammarLexer
import QuestionnaireLanguageStyleGrammarParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import qls.model.StyleSheet


class QlsParser {
    fun parse(): StyleSheet {
        val fileStream = javaClass.getResource("/sample/Style.shiba").openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionnaireLanguageStyleGrammarLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionnaireLanguageStyleGrammarParser(tokens)

        val visitor = QuestionnaireLanguageStyleVisitor()

        val ast = visitor.visit(parser.stylesheet())

        // TODO add checks

        return ast as StyleSheet
    }

}