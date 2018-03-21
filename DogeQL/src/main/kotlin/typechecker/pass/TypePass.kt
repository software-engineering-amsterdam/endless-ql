package typechecker.pass

import common.Name
import data.question.SymbolType
import data.symbol.SymbolTable
import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
import typechecker.NodePass
import typechecker.TypeCheckResult
import typechecker.TypeLocation
import typechecker.TypeRedefinitionError

class TypePass(result: TypeCheckResult, val symbolTable: SymbolTable) : NodePass<Unit>(result) {

    override fun visit(node: Node) {
        node.children.forEach { child -> child.accept(this) }
    }

    override fun visit(rootNode: RootNode) {
        rootNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(questionNode: QuestionNode) {
        val expectedType = questionNode.question.value.type
        val referenceType = getTypeForReference(questionNode.question.name)

        if (expectedType != referenceType) {
            val original = TypeLocation(
                    questionNode.question.name,
                    questionNode.question.value.type,
                    questionNode.question.nameLocation
            )

            val error = TypeRedefinitionError(original, referenceType)
            result.typeConflicts.add(error)
        }

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {
        val referenceType = getTypeForReference(expressionNode.reference)

        if (referenceType == SymbolType.UNDEFINED) {
            val original = TypeLocation(
                    expressionNode.reference,
                    referenceType,
                    expressionNode.sourceLocation
            )

            val error = TypeRedefinitionError(original, referenceType)

            result.typeErrors.add(error)
        }

        expressionNode.children.forEach { child -> child.accept(this) }
    }

    fun getTypeForReference(reference: Name): SymbolType {
        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null) {
            throw IllegalStateException("No symbol defined for $reference")
        }

        if (symbol.expression == null) {
            return symbol.value.type
        }

        return symbol.expression.determineType(symbolTable)
    }

}