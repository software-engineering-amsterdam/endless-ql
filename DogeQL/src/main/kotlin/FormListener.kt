import symbol.BooleanValue
import symbol.IntegerValue
import symbol.SymbolTable
import symbol.SymbolValue
import java.util.*

class FormListener : QuestionareLanguageParserBaseListener() {

    val table = SymbolTable()
    val stack = ArrayDeque<ExprNode>()

    override fun exitForm(ctx: QuestionareLanguageParser.FormContext?) {
        stack.forEach {
            it.print()
            println()
        }
    }

    override fun exitQuestion(ctx: QuestionareLanguageParser.QuestionContext) {
        val text = ctx.STRING_LITERAL().text
        val type = ctx.TYPE().text
        val identifier = ctx.IDENTIFIER().text
        val question = Question(text, identifier, type)

        table.register(identifier, factory(type))
    }

    fun factory(type: String): SymbolValue = when (type) {
        "boolean" -> BooleanValue(false)
        "integer" -> IntegerValue(1)
        else      -> throw IllegalArgumentException("Fuck")
    }


    override fun enterExpr(ctx: QuestionareLanguageParser.ExprContext?) {
        println("Enter: ${ctx.toString()}")
    }

    override fun exitExpr(ctx: QuestionareLanguageParser.ExprContext?) {
        println("Exit: $ctx")

        if(ctx?.childCount == 1){
            val node = ExprNode(ctx.IDENTIFIER().text)
            stack.push(node)
        }else if (ctx?.childCount == 3){
            if (ctx.getChild(0).text == "(") {
                return
            }

            val right = stack.pop()
            val left = stack.pop()
            val node = ExprNode(ctx.getChild(1).text, left, right)
            stack.push(node)

            val a = 1
        }

    }


}

data class ExprNode(val identifier: String, val leftNode: ExprNode? = null, val rightNode: ExprNode? = null) {

    fun print() {
        print("(")
        leftNode?.print()
        print(identifier)
        rightNode?.print()
        print(")")
    }

}