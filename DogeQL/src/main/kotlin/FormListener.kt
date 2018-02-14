import java.util.ArrayList

class FormListener : QuestionareLanguageParserBaseListener() {

    var form: Form? = null
    val questions: MutableCollection<Question> = ArrayList()

    override fun exitForm(ctx: QuestionareLanguageParser.FormContext) {
        val identifier = ctx.IDENTIFIER().text

        form = Form(identifier, questions)
    }

    override fun exitQuestion(ctx: QuestionareLanguageParser.QuestionContext) {
        val text = ctx.STRING_LITERAL().text
        val type = ctx.TYPE().text
        val identifier = ctx.IDENTIFIER().text
        val question = Question(text, identifier, type)

        questions.add(question)
    }
}
