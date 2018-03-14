package ast

import QuestionareLanguageLexer
import QuestionareLanguageParser
import node.Node
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class DogeParser {

    fun parse(): Node {
        val fileStream = javaClass.getResource("/sample/TestQuestionare.doge").openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionareLanguageLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionareLanguageParser(tokens)
        val walker = ParseTreeWalker.DEFAULT
        val listener = DogeListener()

        walker.walk(listener, parser.form())

        return listener.getParsedQuestionareLanguage()
    }

}