package doge.ast

import QuestionnaireLanguageGrammarLexer
import QuestionnaireLanguageGrammarParser
import doge.data.question.Question
import doge.typechecker.TypeChecker
import doge.visitor.UiVisitor
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream


class DogeParser {

    fun parse(): List<Question> {
        val fileName = "/sample/TestQuestionare.doge"
        val fileStream = javaClass.getResource(fileName).openStream()

        val stream = ANTLRInputStream(fileStream)
        val lexer = QuestionnaireLanguageGrammarLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = QuestionnaireLanguageGrammarParser(tokens)

        val visitor = QuestionnaireLanguageVisitor()

        val ast = visitor.visit(parser.form())

        fileStream.close()

        TypeChecker(fileName, ast).check()

        val questions = UiVisitor().visit(ast)

//
//        CircularDependencyVisitor().visit(ast)

//        val listener = DogeListener()

//        walker.walk(listener, parser.form())

//        val tree = listener.getParsedDogeLanguage()

//        val result = TypeChecker(listener.symbolTable).check(tree)

//        if (result.hasErrors()) {
//            result.printErrors()
//
//            throw Exception() // TODO: fix this flow
//        }

        return questions
    }

}