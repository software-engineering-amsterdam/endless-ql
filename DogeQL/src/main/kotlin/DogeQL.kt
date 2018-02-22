import org.kodein.Kodein
import org.kodein.generic.bind
import org.kodein.generic.instance
import org.kodein.generic.provider
import org.kodein.newInstance

class DogeQL {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

////            val userInput = readLine()
//            val file = File(this::class.java.getResource("sample" + File.separator + "TestQuestionare.doge").toURI())
//            var content = file.readText()
//            val stream = ANTLRInputStream(content)
//
//            val lexer = QuestionareLanguageLexer(stream)
//            val tokens = CommonTokenStream(lexer)
//            val parser = QuestionareLanguageParser(tokens)
//            val ctx = parser.form()
//
//
//            // Traverse AST post-order
//            val listener = FormListener()
//
//            ParseTreeWalker.DEFAULT.walk(listener, ctx)


            val kodein = Kodein {
//                bind() from provider { Person() }
//                bind<Person>() with provider { Person() }
                bind<Employee>() with provider {  Employee(instance()) }
//                bind<Company>() with  provider { Company(instance()) }
            }

//            val company: Company by kodein.instance()
            val company by kodein.newInstance { Company(instance()) }

            company.test()
        }
    }

}