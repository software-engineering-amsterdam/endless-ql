package typechecker

import data.symbol.SymbolTable
import node.Node
import typechecker.pass.DuplicatePass
import typechecker.pass.ScopePass

class TypeChecker(val symbolTable: SymbolTable) {

    fun check(tree: Node): TypeCheckResult {
        val result = TypeCheckResult()

        ScopePass(result, symbolTable).visit(tree)
        DuplicatePass(result).visit(tree)

        return result
    }

}