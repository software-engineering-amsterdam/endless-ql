package ui.visitor

import doge.ast.node.Block
import doge.ast.node.Form
import doge.ast.node.IfStatement
import doge.ast.node.QuestionStatement
import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.common.Name
import ui.model.domain.Question
import doge.data.symbol.SymbolTable
import doge.visitor.EvaluationVisitor
import doge.visitor.QuestionnaireASTBaseVisitor

class QuestionVisitor(private val symbolTable: SymbolTable) : QuestionnaireASTBaseVisitor<List<Question>> {

    override fun visit(form: Form): List<Question> {
        return form.block.accept(this)
    }

    override fun visit(block: Block): List<Question> {
        return block.statements.flatMap { statement ->
            statement.accept(this)
        }.toList()
    }

    override fun visit(ifStatement: IfStatement): List<Question> {
        val result = ifStatement.expression.accept(EvaluationVisitor.default(symbolTable))

        if (result.booleanValue.value) {
            return ifStatement.block.accept(this)
        }

        return listOf()
    }

    override fun visit(questionStatement: QuestionStatement): List<Question> {
        val name = questionStatement.name
        val label = questionStatement.label
        val type = questionStatement.type
        var value = symbolTable.findSymbol(name.text)
        val readOnly = false

        if (questionStatement.expression != null) {
            value = questionStatement.expression.accept(EvaluationVisitor.default(symbolTable))
        }

        return listOf(Question(name.text, label.text, value!!, false))
    }

    override fun visit(binaryExpression: BinaryExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(unaryExpression: UnaryExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(referenceExpression: ReferenceExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(literalExpression: LiteralExpression): List<Question> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}