package expression.visitor.evaluation

import data.value.BaseSymbolValue

interface ReferenceProvider {

    fun findReference(name: String): BaseSymbolValue

}
