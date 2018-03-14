package data.symbol

import common.Name
import data.question.SymbolType
import data.value.BaseSymbolValue
import expression.Expression

class SymbolTable {

    private var symbols = HashMap<Name, Symbol>()

    private val internalNamePrefix = "@__VAR__"
    private var internalNameIndex = 0

    fun registerSymbol(type: SymbolType, value: Expression? = null): SymbolRegistrationResult {
        val name = nextFreeInternalName()
        return registerSymbol(name, type, value)
    }

    fun registerSymbol(name: Name, type: SymbolType, value: Expression? = null): SymbolRegistrationResult {
        val previousSymbol = findSymbol(name)

        return when {

            previousSymbol == null -> {
                val symbol = Symbol(type, value)
                symbols[name] = symbol
                SymbolRegistrationResult.Registered(name, symbol)
            }

            previousSymbol.value.type == type -> {
                SymbolRegistrationResult.AlreadyRegistered(name)
            }

            else -> {
                SymbolRegistrationResult.AlreadyRegisteredTypeMismatch(name, previousSymbol)
            }

        }
    }

    fun assign(name: String, value: BaseSymbolValue) {
        findSymbol(name)?.let {
            it.assign(value)
        } ?: run {
            throw NoSuchElementException("Unable to find $name in symbol table")
        }
    }

    fun findSymbol(name: Name): Symbol? = symbols[name]

    private fun nextFreeInternalName(): Name {
        val name = internalNamePrefix + internalNameIndex

        ++internalNameIndex

        return name
    }

}