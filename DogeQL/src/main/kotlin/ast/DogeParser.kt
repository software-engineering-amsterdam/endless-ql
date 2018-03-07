package ast

import QuestionareLanguageLexer
import QuestionareLanguageParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class DogeParser {

    fun parse() {
        val fileStream = javaClass.getResource("/sample/TestQuestionare.doge").openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionareLanguageLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionareLanguageParser(tokens)
        val walker = ParseTreeWalker()

        walker.walk(DogeListener(), parser.form())
    }

}