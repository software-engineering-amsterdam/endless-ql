package typechecker.pass

import common.Name
import data.symbol.SymbolTable
import expression.SourceLocation
import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
import typechecker.NodePass
import typechecker.TokenLocation
import typechecker.TypeCheckResult

class ScopePass(result: TypeCheckResult, val symbolTable: SymbolTable) : NodePass<Unit>(result) {

    override fun visit(node: Node) {
        node.children.forEach { child -> child.accept(this) }
    }

    override fun visit(rootNode: RootNode) {
        rootNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(questionNode: QuestionNode) {
        findAllUndefinedReferences(questionNode.question.name, questionNode.question.nameLocation)

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {
        findAllUndefinedReferences(expressionNode.reference, expressionNode.sourceLocation)

        expressionNode.children.forEach { child -> child.accept(this) }
    }

    private fun findAllUndefinedReferences(reference: Name, sourceLocation: SourceLocation) {
        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null) {
            val error = TokenLocation(reference, sourceLocation)
            result.undefinedReferences.add(error)
        } else if (symbol.expression != null) {
            val references = symbol.expression.allReferences()

            references.forEach { r -> findAllUndefinedReferences(r.name, r.sourceLocation) }
        }
    }

}