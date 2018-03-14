package ast

import QuestionareLanguageParser
import QuestionareLanguageParserBaseListener
import node.Node
import data.question.Question
import data.value.*
import expression.BinaryExpression
import expression.LiteralExpression
import expression.UnaryExpression
import expression.operation.BinaryOperation
import expression.operation.UnaryOperation
import java.math.BigDecimal

class DogeListener : QuestionareLanguageParserBaseListener() {

    private val expressionBuilder = ExpressionBuilder()
    private val formTreeBuilder = FormTreeBuilder()

    override fun enterBlock(ctx: QuestionareLanguageParser.BlockContext?) {
        if (!expressionBuilder.isEmpty())
            formTreeBuilder.pushExpression(expressionBuilder.first())
    }

    override fun exitIfStatement(ctx: QuestionareLanguageParser.IfStatementContext?) {
        formTreeBuilder.build() //Build subtree
    }

    override fun exitQuestionStatement(ctx: QuestionareLanguageParser.QuestionStatementContext?) {
        requireNotNull(ctx)

        val context = ctx!!

        val label = context.LIT_STRING().text
        val name = context.NAME().text
        val type = context.TYPE().text

        val question = Question(name, label, convertType(type))

        formTreeBuilder.pushQuestion(question)
    }

    override fun exitExpression(ctx: QuestionareLanguageParser.ExpressionContext?) {
        requireNotNull(ctx)

        val context = ctx!!

        println(ctx.text)

        context.NAME()?.let {
            return
        }

        context.NOT()?.let {
            pushUnaryExpression(UnaryOperation.Negate)
            return
        }

        context.MUL()?.let {
            pushBinaryExpression(BinaryOperation.Multiply)
            return
        }

        context.DIV()?.let {
            pushBinaryExpression(BinaryOperation.Divide)
            return
        }

        context.ADD()?.let {
            pushBinaryExpression(BinaryOperation.Add)
            return
        }

        context.SUB()?.let {
            pushBinaryExpression(BinaryOperation.Subtract)
            return
        }

        context.LT()?.let {
            pushBinaryExpression(BinaryOperation.Less)
            return
        }

        context.GT()?.let {
            pushBinaryExpression(BinaryOperation.Greater)
            return
        }

        context.LE()?.let {
            pushBinaryExpression(BinaryOperation.LessOrEqual)
            return
        }

        context.GE()?.let {
            pushBinaryExpression(BinaryOperation.GreaterOrEqual)
            return
        }

        context.EQUAL()?.let {
            pushBinaryExpression(BinaryOperation.Equal)
            return
        }

        context.NOTEQUAL()?.let {
            pushBinaryExpression(BinaryOperation.NotEqual)
            return
        }

        context.AND()?.let {
            pushBinaryExpression(BinaryOperation.And)
            return
        }

        context.OR()?.let {
            pushBinaryExpression(BinaryOperation.Or)
            return
        }

    }

    override fun exitLiteral(ctx: QuestionareLanguageParser.LiteralContext?) {
        requireNotNull(ctx)

        val context = ctx!!

        println(ctx.text)

        context.LIT_BOOLEAN()?.let {
            println("Boolean ${ctx.text}")

            expressionBuilder.push(
                    LiteralExpression(BooleanValue(it.text))
            )

            return
        }

        context.LIT_INTEGER()?.let {
            println("Integer ${ctx.text}")

            expressionBuilder.push(
                    LiteralExpression(IntegerValue(it.text))
            )

            return
        }

        context.LIT_DECIMAL()?.let {
            println("Decimal ${ctx.text}")

            expressionBuilder.push(
                    LiteralExpression(DecimalValue(it.text))
            )

            return
        }

        context.LIT_STRING()?.let {
            println("String ${ctx.text}")

            expressionBuilder.push(
                    LiteralExpression(StringValue(it.text))
            )

            return
        }

        context.LIT_COLOR()?.let {
            println("Color ${ctx.text}")

            expressionBuilder.push(
                    LiteralExpression(ColorValue(it.text))
            )

            return
        }
    }

    private fun pushUnaryExpression(operation: UnaryOperation) {
        val value = expressionBuilder.pop()

        expressionBuilder.push(
                UnaryExpression(value, operation)
        )
    }

    private fun pushBinaryExpression(operation: BinaryOperation) {
        val right = expressionBuilder.pop()
        val left = expressionBuilder.pop()

        expressionBuilder.push(
                BinaryExpression(left, right, operation)
        )
    }

    private fun convertType(type: String) = when (type) {
        "boolean" -> BooleanValue(false)
        "int" -> IntegerValue(0)
        "string" -> StringValue("")
        "money" -> MoneyValue(BigDecimal.ZERO)
        "decimal" -> DecimalValue(0)
//        "date" -> DateValue(0)
        else -> BooleanValue(false)//TODO refactor remove default
    }

    fun getParsedDogeLanguage(): Node {
        return formTreeBuilder.build()
    }
}