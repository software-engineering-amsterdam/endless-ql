package doge.visitor.duplication

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

class DuplicateQuestionVisitor(private val context: DuplicationErrorContext) : QuestionnaireASTBaseVisitor<Unit> {

    private val labels = mutableListOf<Identifier>()
    private val names = mutableListOf<Identifier>()

    override fun visit(form: Form) {
        visit(form.block)
    }

    override fun visit(block: Block) {
        block.statements.forEach { visit(it) }
    }

    override fun visit(ifStatement: IfStatement) {
        visit(ifStatement.block)
    }

    override fun visit(questionStatement: QuestionStatement) {
        val label = questionStatement.label
        labels.find { entry -> entry.text == label.text }?.let {
            context.labelDuplications += DuplicationError(label.text, it.location, label.location)
        } ?: run {
            labels += label
        }

        val name = questionStatement.name
        names.find { entry -> entry.text == name.text }?.let {
            context.nameDuplications += DuplicationError(name.text, it.location, name.location)
        } ?: run {
            names += name
        }
    }

    override fun visit(binaryExpression: BinaryExpression) {

    }

    override fun visit(unaryExpression: UnaryExpression) {

    }

    override fun visit(referenceExpression: ReferenceExpression) {

    }

    override fun visit(literalExpression: LiteralExpression) {

    }

}