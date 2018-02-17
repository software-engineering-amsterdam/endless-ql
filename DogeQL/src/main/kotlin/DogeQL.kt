import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.File

class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

//            val userInput = readLine()
            val file = File(this::class.java.getResource("sample" + File.separator + "TestQuestionare.doge").toURI())
            var content = file.readText()
            val stream = ANTLRInputStream(content)

            val lexer = QuestionareLanguageLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val parser = QuestionareLanguageParser(tokens)
            val ctx = parser.form()


            // Traverse AST post-order
            val listener = FormListener()

            ParseTreeWalker.DEFAULT.walk(listener, ctx)
        }
    }

}