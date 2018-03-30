package doge.typechecker

import doge.ast.node.QLNode
import doge.data.symbol.SymbolTable
import doge.typechecker.circular.CircularDependencyErrorContext
import doge.typechecker.circular.CircularDependencyVisitor
import doge.typechecker.circular.QuestionLookupVisitor
import doge.typechecker.duplication.DuplicateQuestionVisitor
import doge.typechecker.duplication.DuplicationErrorContext
import doge.typechecker.scope.ScopeErrorContext
import doge.typechecker.scope.ScopeVisitor
import doge.typechecker.type.TypeErrorContext
import doge.typechecker.type.TypeVisitor

class TypeChecker(private val fileName: String, private val symbolTable: SymbolTable, private val root: QLNode) {

    fun check(): Boolean {
        val allErrors = mutableListOf<String>()

        allErrors += checkDuplicates().collect()
        allErrors += checkCircularDependencies().collect()
        allErrors += checkUndefinedReferences().collect()

        if (allErrors.isNotEmpty()) {
            printErrors(allErrors)
            return false
        }

        allErrors += checkTypes().collect()

        if (allErrors.isNotEmpty()) {
            printErrors(allErrors)
            return false
        }

        return true
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

    private fun checkTypes(): TypeErrorContext {
        return TypeErrorContext().apply {
            TypeVisitor(
                    this,
                    { reference ->
                        symbolTable.findSymbol(reference)?.let {
                            it
                        } ?: run {
                            QuestionLookupVisitor(reference).visit(root)?.let {
                                symbolTable.registerSymbol(it.name.text, it.type.type.getDefaultInstance())
                                symbolTable.findSymbol(reference)
                            }
                        }
                    },
                    { name, value ->
                        symbolTable.registerSymbol(name, value)
                    }
            ).visit(root)
        }
    }

    private fun collectFormattedErrors(errors: List<String>): List<String> {
        val indexTextLength = errors.size.toString().length

        val errorFormat = "Error [%${indexTextLength}d/%${indexTextLength}d]: %s"

        return errors.mapIndexed { index, error ->
            errorFormat.format(index + 1, errors.size, error)
        }
    }

    private fun printErrors(errors: List<String>) {
        collectFormattedErrors(errors).forEach { println(it) }
    }

}