package doge.typechecker

import doge.ast.node.QLNode
import doge.typechecker.circular.CircularDependencyErrorContext
import doge.typechecker.circular.CircularDependencyVisitor
import doge.typechecker.circular.QuestionLookupVisitor
import doge.typechecker.duplication.DuplicateQuestionVisitor
import doge.typechecker.duplication.DuplicationErrorContext
import doge.typechecker.scope.ScopeErrorContext
import doge.typechecker.scope.ScopeVisitor

class TypeChecker(private val fileName: String, private val root: QLNode) {

    fun check() {
        val duplicateErrors = checkDuplicates().collect()
        val circularErrors = checkCircularDependencies().collect()
        val undefinedErrors = checkUndefinedReferences().collect()
        val allErrors = duplicateErrors + circularErrors + undefinedErrors
        val formattedErrors = collectFormattedErrors(allErrors)

        formattedErrors.forEach { println(it) }
    }

    private fun checkDuplicates(): DuplicationErrorContext {
        return DuplicationErrorContext().apply {
            DuplicateQuestionVisitor(this).visit(root)
        }
    }

    private fun checkCircularDependencies(): CircularDependencyErrorContext {
        return CircularDependencyErrorContext().apply {
            CircularDependencyVisitor(this, { reference ->
                QuestionLookupVisitor(reference).visit(root)
            }).visit(root)
        }
    }

    private fun checkUndefinedReferences(): ScopeErrorContext {
        return ScopeErrorContext().apply {
            ScopeVisitor(this).visit(root)
        }
    }

    private fun collectFormattedErrors(errors: List<String>): List<String> {
        val indexTextLength = errors.size.toString().length

        val errorFormat = "Error [%${indexTextLength}d/%${indexTextLength}d]: %s"

        return errors.mapIndexed { index, error ->
            errorFormat.format(index + 1, errors.size, error)
        }
    }

}