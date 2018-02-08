import grammar.SimpleGrammarExampleLexer
import grammar.SimpleGrammarExampleParser
import main.java.ExampleVisitor
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File


class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {


//            val userInput = readLine()
            val file = File("sample"+File.separator+"TestQuestionare.doge")
            var content:String = file.readText()
            val stream = ANTLRInputStream(content)

            val lexer = SimpleGrammarExampleLexer(stream)
            val tokens = CommonTokenStream(lexer)
            val parser = SimpleGrammarExampleParser(tokens)
            val ctx = parser.prog()

            // Traverse AST post-order
            val visitor = ExampleVisitor().visitProg(ctx)
        }
    }

}