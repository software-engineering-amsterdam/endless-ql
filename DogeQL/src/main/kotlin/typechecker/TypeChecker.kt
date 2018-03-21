package typechecker

import data.symbol.SymbolTable
import node.Node
import typechecker.pass.DuplicatePass
import typechecker.pass.ScopePass
import typechecker.pass.TypePass

class TypeChecker(val symbolTable: SymbolTable) {

    fun check(tree: Node): TypeCheckResult {
        val result = TypeCheckResult()

        DuplicatePass(result).visit(tree)
        ScopePass(result, symbolTable).visit(tree)

        if (result.hasErrors()) {
            return result
        }

        TypePass(result, symbolTable).visit(tree)

        return result
    }

}