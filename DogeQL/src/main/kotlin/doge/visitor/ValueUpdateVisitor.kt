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

class ValueUpdateVisitor(
        private val referenceProvider: (reference: String) -> BaseSymbolValue,
        private val referenceUpdater: (reference: String, value: BaseSymbolValue) -> Unit
) : QuestionnaireASTBaseVisitor<Unit> {

    companion object {
        fun default(symbolTable: SymbolTable): ValueUpdateVisitor {
            return ValueUpdateVisitor(
                    { reference ->
                        symbolTable.findSymbol(reference)
                                ?: throw IllegalStateException("Unable to find reference $reference")
                    },
                    { name, value ->
                        symbolTable.assign(name, value)
                    }
            )
        }
    }

    override fun visit(form: Form) {
        visit(form.block)
    }

    override fun visit(block: Block) {
        block.statements.forEach { visit(it) }
    }

    override fun visit(ifStatement: IfStatement) {
        visit(ifStatement.block)
    }

    override fun visit(questionStatement: QuestionStatement) {
        questionStatement.expression?.let {
            referenceUpdater(questionStatement.name.text, EvaluationVisitor(referenceProvider).visit(it))
        }
    }

    override fun visit(binaryExpression: BinaryExpression) {

    }

    override fun visit(unaryExpression: UnaryExpression) {

    }

    override fun visit(referenceExpression: ReferenceExpression) {

    }

    override fun visit(literalExpression: LiteralExpression) {

    }

}