package form.ast

import QuestionareLanguageLexer
import QuestionareLanguageParser
import form.node.Node
import form.typechecker.TypeChecker
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

        val tree = listener.getParsedDogeLanguage()

        val result = TypeChecker(listener.symbolTable).check(tree)

        if (result.hasErrors()) {
            result.printErrors()

            throw Exception() // TODO: fix this flow
        }

        return tree
    }

}