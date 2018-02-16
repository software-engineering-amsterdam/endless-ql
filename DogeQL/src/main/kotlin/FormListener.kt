import Tree.ExpressionNode
import data.*
import java.util.*

class FormListener : QuestionareLanguageParserBaseListener() {

    val table = QuestionTable()
    val stack = ArrayDeque<ExpressionNode>()

    override fun exitForm(ctx: QuestionareLanguageParser.FormContext?) {
        stack.forEach {
            it.print()
            println()
        }

        table.print()
    }

    override fun exitQuestion(ctx: QuestionareLanguageParser.QuestionContext) {
        val text = ctx.STRING_LITERAL().text
        val type = ctx.TYPE().text
        val identifier = ctx.IDENTIFIER().text

        val question = Question(text, typeParser(type))

        table.register(identifier, question)
    }

    override fun exitExpr(ctx: QuestionareLanguageParser.ExprContext?) {

        if(ctx?.childCount == 1){
            val identifier = ctx.IDENTIFIER().text
            val node = ExpressionNode(identifier)
            stack.push(node)
        }else if (ctx?.childCount == 3){
            if (ctx.getChild(0).text == "(") {
                return
            }

            val right = stack.pop()
            val left = stack.pop()
            val node = ExpressionNode(ctx.getChild(1).text, left, right)
            stack.push(node)
        }
    }
}


fun typeParser(type: String): BaseSymbolValue = when (type) {
    "boolean" -> BooleanValue(false)
    "integer" -> IntegerValue(1)
    else      -> throw IllegalArgumentException("Fuck")
}

