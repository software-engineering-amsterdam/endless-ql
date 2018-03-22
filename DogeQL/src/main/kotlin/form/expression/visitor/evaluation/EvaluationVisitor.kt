package form.expression.visitor.evaluation

import form.data.symbol.SymbolTable
import form.data.value.BaseSymbolValue
import form.expression.BinaryExpression
import form.expression.LiteralExpression
import form.expression.ReferenceExpression
import form.expression.UnaryExpression
import form.expression.visitor.ExpressionVisitor


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