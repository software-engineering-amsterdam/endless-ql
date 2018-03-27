package form.typechecker.pass

import form.common.Name
import form.data.question.SymbolType
import form.data.symbol.SymbolTable
import form.node.ExpressionNode
import form.node.Node
import form.node.QuestionNode
import form.node.RootNode
import form.typechecker.NodePass
import form.typechecker.TypeCheckResult
import form.typechecker.TypeLocation
import form.typechecker.TypeRedefinitionError

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