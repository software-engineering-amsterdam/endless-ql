package typechecker

import common.Name
import data.question.SymbolType

class TypeCheckResult {

    val undefinedReferences = mutableListOf<Name>()
    val duplicateLabels = mutableListOf<Name>()
    val duplicateNames = mutableListOf<Name>()
    val typeConflicts = mutableListOf<TypeConflict>()
    val typeErrors = mutableListOf<Name>()

    data class TypeConflict(val expectedType: SymbolType, val actualType: SymbolType)

}