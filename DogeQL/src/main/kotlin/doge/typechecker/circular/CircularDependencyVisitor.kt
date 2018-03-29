package doge.typechecker.circular

import doge.ast.location.Identifier
import doge.ast.node.Block
import doge.ast.node.Form
import doge.ast.node.IfStatement
import doge.ast.node.QuestionStatement
import doge.ast.node.expression.BinaryExpression
import doge.ast.node.expression.LiteralExpression
import doge.ast.node.expression.ReferenceExpression
import doge.ast.node.expression.UnaryExpression
import doge.visitor.QuestionnaireASTBaseVisitor
import java.util.*

class CircularDependencyVisitor(
        private val context: CircularDependencyErrorContext,
        private val requestQuestion: (reference: String) -> QuestionStatement?
) : QuestionnaireASTBaseVisitor<Unit> {
    private val visitedReferences = ArrayDeque<Identifier>()

    override fun visit(form: Form) {
        visit(form.block)
    }

    override fun visit(block: Block) {
        block.statements.forEach { visit(it) }
    }

    override fun visit(ifStatement: IfStatement) {
        visit(ifStatement.expression)
        visit(ifStatement.block)
    }

    override fun visit(questionStatement: QuestionStatement) {
        questionStatement.expression?.let {
            visit(it)
        }
    }

    override fun visit(binaryExpression: BinaryExpression) {
        visit(binaryExpression.left)
        visit(binaryExpression.right)
    }

    override fun visit(unaryExpression: UnaryExpression) {
        visit(unaryExpression.next)
    }

    override fun visit(referenceExpression: ReferenceExpression) {
        val reference = referenceExpression.name

        visitedReferences.find { it.text == reference.text }?.let {
            val first = visitedReferences.first
            val chain = visitedReferences.drop(1)
            val error = CircularDependencyError(first, chain)
            context.errors += error
            return
        }

        visitedReferences.push(referenceExpression.name)

        requestQuestion(reference.text)?.let {
            visit(it)
        }

        visitedReferences.pop()
    }

    override fun visit(literalExpression: LiteralExpression) {

    }
}

