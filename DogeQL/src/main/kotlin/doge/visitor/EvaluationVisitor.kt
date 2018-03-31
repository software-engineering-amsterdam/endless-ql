package doge.visitor

import doge.ast.node.Block
import doge.ast.node.Form
import doge.ast.node.IfStatement
import doge.ast.node.QuestionStatement
import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.data.symbol.SymbolTable
import doge.data.value.BaseSymbolValue

class EvaluationVisitor(
        private val referenceProvider: (reference: String) -> BaseSymbolValue
): QuestionnaireASTBaseVisitor<BaseSymbolValue> {

    companion object {
        fun default(symbolTable: SymbolTable): EvaluationVisitor {
            return EvaluationVisitor({ reference ->
                symbolTable.findSymbol(reference)
                        ?: throw IllegalStateException("Unable to find reference $reference")
            })
        }
    }

    override fun visit(form: Form): BaseSymbolValue {
        throw IllegalStateException("Unable to evaluate form")
    }

    override fun visit(block: Block): BaseSymbolValue {
        throw IllegalStateException("Unable to evaluate block")
    }

    override fun visit(ifStatement: IfStatement): BaseSymbolValue {
        throw IllegalStateException("Unable to evaluate if statement")
    }

    override fun visit(questionStatement: QuestionStatement): BaseSymbolValue {
        throw IllegalStateException("Unable to evaluate question statement")
    }

    override fun visit(binaryExpression: BinaryExpression): BaseSymbolValue {
        val leftValue = visit(binaryExpression.left)
        val rightValue = visit(binaryExpression.right)

        return binaryExpression.operation(leftValue, rightValue)
    }

    override fun visit(unaryExpression: UnaryExpression): BaseSymbolValue {
        val nextValue = visit(unaryExpression.next)

        return unaryExpression.operation(nextValue)
    }

    override fun visit(referenceExpression: ReferenceExpression): BaseSymbolValue {
        return referenceProvider(referenceExpression.name.text)
    }

    override fun visit(literalExpression: LiteralExpression): BaseSymbolValue {
        return literalExpression.value
    }

}