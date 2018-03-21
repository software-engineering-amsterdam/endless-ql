package typechecker.pass

import common.Name
import data.symbol.Symbol
import data.symbol.SymbolTable
import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
import typechecker.NodePass
import typechecker.TokenLocation
import typechecker.TypeCheckResult
import java.util.*

class ScopePass(result: TypeCheckResult, val symbolTable: SymbolTable) : NodePass<Unit>(result) {

    private val visibleReferences = ArrayDeque<Name>()

    override fun visit(node: Node) {
        node.children.forEach { child -> child.accept(this) }
    }

    override fun visit(rootNode: RootNode) {
        rootNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(questionNode: QuestionNode) {
        visibleReferences.push(questionNode.question.name)

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {

        val reference = expressionNode.reference

        if (!visibleReferences.contains(reference)) {

            // Reference is not in scope, but this reference might be pointing to a generated expression.
            // Lets retrieve that expression and check its primitives.

            val symbol = symbolTable.findSymbol(reference)

            if (symbol == null) {
                throw IllegalStateException("Unable to find symbol for reference $reference")
            } else {
                checkReferencesForSymbol(expressionNode, symbol)
            }
        }

        expressionNode.children.forEach { child -> child.accept(this) }
    }

    private fun checkReferencesForSymbol(expressionNode: ExpressionNode, symbol: Symbol) {
        if (symbol.expression == null) {
            val error = TokenLocation(expressionNode.reference, expressionNode.sourceLocation)
            result.undefinedReferences.add(error)
        } else {
            val references = symbol.expression.allReferences()

            references.forEach {

                if (!visibleReferences.contains(it.name)) {
                    val error = TokenLocation(it.name, it.sourceLocation)
                    result.undefinedReferences.add(error)
                }

            }
        }
    }

}