package expression.visitor.evaluation

import data.symbol.SymbolTable
import data.value.BaseSymbolValue
import expression.BinaryExpression
import expression.LiteralExpression
import expression.ReferenceExpression
import expression.UnaryExpression
import expression.visitor.ExpressionVisitor


class EvaluationVisitor(private val symbolTable: SymbolTable) : ExpressionVisitor<BaseSymbolValue> {

    override fun visit(literal: LiteralExpression): BaseSymbolValue {
        return literal.value
    }

    override fun visit(reference: ReferenceExpression): BaseSymbolValue {
        symbolTable.findSymbol(reference.name)?.let {
            it.evaluate(symbolTable)
            return it.value
        } ?: run {
            throw NoSuchElementException("Unable to find reference ${reference.name}")
        }
    }

    override fun visit(unary: UnaryExpression): BaseSymbolValue {
        return unary.operation(unary.next.accept(this))
    }

    override fun visit(binary: BinaryExpression): BaseSymbolValue {
        return binary.operation(binary.left.accept(this), binary.right.accept(this))
    }

}