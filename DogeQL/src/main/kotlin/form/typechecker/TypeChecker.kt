package form.typechecker

import form.data.symbol.SymbolTable
import form.node.Node
import form.typechecker.pass.CircularDependencyPass
import form.typechecker.pass.DuplicatePass
import form.typechecker.pass.ScopePass
import form.typechecker.pass.TypePass

class TypeChecker(val symbolTable: SymbolTable) {

    fun check(tree: Node): TypeCheckResult {
        val result = TypeCheckResult()

        DuplicatePass(result).visit(tree)

        CircularDependencyPass(result, symbolTable).visit(tree)
        if (result.hasErrors()) {
            return result
        }

        ScopePass(result, symbolTable).visit(tree)
        if (result.hasErrors()) {
            return result
        }

        TypePass(result, symbolTable).visit(tree)

        return result
    }

}