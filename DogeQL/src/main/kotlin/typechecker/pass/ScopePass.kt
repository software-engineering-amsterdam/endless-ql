package typechecker.pass

import common.Name
import data.symbol.Symbol
import data.symbol.SymbolTable
import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
import typechecker.NodePass
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
                result.undefinedReferences.add(reference)
            } else {
                checkReferencesForSymbol(reference, symbol)
            }
        }

        expressionNode.children.forEach { child -> child.accept(this) }
    }

    private fun checkReferencesForSymbol(reference: Name, symbol: Symbol) {
        if (symbol.expression == null) {
            result.undefinedReferences.add(reference)
        } else {
            val references = symbol.expression.allReferences()

            references.forEach {
                if (!visibleReferences.contains(it)) {
                    result.undefinedReferences.add(it)
                }
            }
        }
    }

}