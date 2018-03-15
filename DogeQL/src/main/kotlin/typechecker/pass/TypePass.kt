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
            result.typeConflicts.add(TypeCheckResult.TypeConflict(expectedType, referenceType))
        }

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {
        val referenceType = getTypeForReference(expressionNode.reference)

        if (referenceType == SymbolType.UNDEFINED) {
            result.typeErrors.add(expressionNode.reference)
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