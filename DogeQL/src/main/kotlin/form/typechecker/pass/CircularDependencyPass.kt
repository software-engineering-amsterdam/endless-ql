package form.typechecker.pass

import form.common.Name
import form.data.symbol.Symbol
import form.data.symbol.SymbolTable
import form.node.ExpressionNode
import form.node.Node
import form.node.QuestionNode
import form.node.RootNode
import form.typechecker.CircularDependencyError
import form.typechecker.NodePass
import form.typechecker.TokenLocation
import form.typechecker.TypeCheckResult

class CircularDependencyPass(result: TypeCheckResult, val symbolTable: SymbolTable) : NodePass<Unit>(result) {

    private data class CircularDependency(val reference: Name, val symbol: Symbol?)

    override fun visit(node: Node) {
        node.children.forEach { child -> child.accept(this) }
    }

    override fun visit(rootNode: RootNode) {
        rootNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(questionNode: QuestionNode) {
        val errorSymbol = findFirstCircularDependencies(questionNode.question.name)

        if (errorSymbol != null) {
            val source = TokenLocation(questionNode.question.name, questionNode.question.nameLocation)
            val error = CircularDependencyError(source, errorSymbol.reference)
            result.circularErrors.add(error)
        }

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {
        val errorSymbol = findFirstCircularDependencies(expressionNode.reference)

        if (errorSymbol != null) {
            val source = TokenLocation(expressionNode.reference, expressionNode.sourceLocation)
            val error = CircularDependencyError(source, errorSymbol.reference)
            result.circularErrors.add(error)
        }

        expressionNode.children.forEach { child -> child.accept(this) }
    }

    private fun findFirstCircularDependencies(
            reference: Name, seenReferences: HashSet<Name> = hashSetOf(reference)
    ): CircularDependency? {

        val symbol = symbolTable.findSymbol(reference) ?: return null

        if (symbol.expression != null) {
            val allReferences = symbol.expression.allReferences()

            for (internalReference in allReferences) {
                val unique = seenReferences.add(internalReference.name)

                if (!unique) {
                    return CircularDependency(internalReference.name, symbolTable.findSymbol(internalReference.name))
                }

                val clash = findFirstCircularDependencies(internalReference.name, seenReferences)

                if (clash != null) {
                    return CircularDependency(internalReference.name, clash.symbol)
                }
            }

        }

        return null
    }

}