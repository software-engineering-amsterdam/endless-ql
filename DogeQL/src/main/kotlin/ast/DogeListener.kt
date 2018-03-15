package ast

import QuestionareLanguageParser
import QuestionareLanguageParserBaseListener
import data.question.Question
import data.question.SymbolType
import data.symbol.SymbolRegistrationResult
import data.symbol.SymbolTable
import data.value.*
import expression.BinaryExpression
import expression.LiteralExpression
import expression.ReferenceExpression
import expression.UnaryExpression
import expression.operation.BinaryOperation
import expression.operation.UnaryOperation
import node.Node
import java.math.BigDecimal

class DogeListener : QuestionareLanguageParserBaseListener() {

    private val expressionBuilder = ExpressionBuilder()
    private val symbolTable = SymbolTable()
    private val formTreeBuilder = FormTreeBuilder(symbolTable)

    private var ifStatementDepth = 0

    override fun exitForm(ctx: QuestionareLanguageParser.FormContext?) {
        val a = 1
    }

    override fun enterBlock(ctx: QuestionareLanguageParser.BlockContext?) {
        if (!expressionBuilder.isEmpty()){
            --ifStatementDepth

            val ifExpression = expressionBuilder.pop()
            val result = symbolTable.registerSymbol(SymbolType.Boolean, ifExpression)

            formTreeBuilder.pushExpression(result.name)
        }
    }

    override fun enterIfStatement(ctx: QuestionareLanguageParser.IfStatementContext?) {
        ++ifStatementDepth
    }

    override fun exitIfStatement(ctx: QuestionareLanguageParser.IfStatementContext?) {
        formTreeBuilder.build()
    }

    override fun exitQuestionStatement(ctx: QuestionareLanguageParser.QuestionStatementContext?) {
        requireNotNull(ctx)
        val context = ctx!!

        val label = context.LIT_STRING().text
        val name = context.NAME().text
        val value = convertType(context.TYPE().text)

        val questionExpression = when {
            ifStatementDepth >= expressionBuilder.size() -> null
            else -> expressionBuilder.pop()
        }

        if (questionExpression != null) {
            if (questionExpression.containsReference()) {
                symbolTable.registerSymbol(name, value.type, questionExpression)
            } else {
                symbolTable.registerSymbol(name, value.type)
                symbolTable.assign(name, questionExpression.evaluate(symbolTable))
            }
        } else {
            symbolTable.registerSymbol(name, value.type)
        }

        val question = Question(name, label, value)
        formTreeBuilder.pushQuestion(question)
    }

    override fun exitExpression(ctx: QuestionareLanguageParser.ExpressionContext?) {
        requireNotNull(ctx)

        val context = ctx!!

        context.NAME()?.let {
            pushReferenceExpression(it.text)
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

        context.LIT_BOOLEAN()?.let {
            pushLiteralExpression(BooleanValue(it.text))
            return
        }

        context.LIT_INTEGER()?.let {
            pushLiteralExpression(IntegerValue(it.text))
            return
        }

        context.LIT_DECIMAL()?.let {
            pushLiteralExpression(DecimalValue(it.text))
            return
        }

        context.LIT_STRING()?.let {
            pushLiteralExpression(StringValue(it.text))
            return
        }

        context.LIT_COLOR()?.let {
            pushLiteralExpression(ColorValue(it.text))
            return
        }
    }

    private fun pushLiteralExpression(value: BaseSymbolValue) {
        expressionBuilder.push(
                LiteralExpression(value)
        )
    }

    private fun pushReferenceExpression(name: String) {
        expressionBuilder.push(
                ReferenceExpression(name, SymbolType.Undecided)
        )
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