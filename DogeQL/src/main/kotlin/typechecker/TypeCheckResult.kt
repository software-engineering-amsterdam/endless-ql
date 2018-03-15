package typechecker

import common.Name

class TypeCheckResult {

    val undefinedReferences = mutableListOf<Name>()
    val duplicateLabels = mutableListOf<Name>()
    val duplicateNames = mutableListOf<Name>()

}