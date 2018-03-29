package doge.typechecker

import doge.ast.node.QLNode
import doge.visitor.duplication.DuplicateQuestionVisitor
import doge.visitor.duplication.DuplicationErrorContext

class TypeChecker(private val fileName: String, private val root: QLNode) {

    fun check() {
        val duplicationErrorContext = checkDuplicates()

        if (duplicationErrorContext.hasErrors()) {
            val errors = duplicationErrorContext.collect()
            val formattedErrors = collectFormattedErrors(errors)

            formattedErrors.forEach { println(it) }
        }
    }

    private fun checkDuplicates(): DuplicationErrorContext {
        val duplicationErrorContext = DuplicationErrorContext()

        DuplicateQuestionVisitor(duplicationErrorContext).visit(root)

        return duplicationErrorContext
    }

    private fun collectFormattedErrors(errors: List<String>): List<String> {
        val indexTextLength = errors.size.toString().length

        val errorFormat = "Error [%${indexTextLength}d/%${indexTextLength}d]: %s"

        return errors.mapIndexed { index, error ->
            errorFormat.format(index + 1, errors.size, error)
        }
    }

    private fun fileStream() = javaClass.getResource(fileName).openStream()

}