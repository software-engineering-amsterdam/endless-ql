package symbol

import common.Identifier

class SymbolTable {

    val table = HashMap<Identifier, SymbolValue>()

    fun lookUp(identifier: Identifier): SymbolValue? {
        return table[identifier]
    }

    fun register(identifier: Identifier, symbol: SymbolValue) {
        table[identifier] = symbol
    }

}