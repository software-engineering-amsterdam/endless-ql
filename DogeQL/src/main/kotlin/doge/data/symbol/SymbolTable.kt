package doge.data.symbol

import doge.common.Name
import doge.data.value.BaseSymbolValue

class SymbolTable {

    private var symbols = HashMap<Name, BaseSymbolValue>()

    fun registerSymbol(name: Name, value: BaseSymbolValue): SymbolRegistrationResult {
        val previousSymbol = findSymbol(name)

        return when {
            previousSymbol == null -> { assign(name, value); SymbolRegistrationResult.Registered() }
            previousSymbol.type == value.type -> SymbolRegistrationResult.AlreadyRegistered()
            else -> SymbolRegistrationResult.AlreadyRegisteredTypeMismatch(previousSymbol)
        }
    }

    fun assign(name: String, value: BaseSymbolValue) {
        symbols[name] = value
    }

    fun findSymbol(name: Name): BaseSymbolValue? = symbols[name]

}