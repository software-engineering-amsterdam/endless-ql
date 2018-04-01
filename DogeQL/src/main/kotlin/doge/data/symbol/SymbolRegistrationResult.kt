package doge.data.symbol

import doge.data.value.BaseSymbolValue

sealed class SymbolRegistrationResult {
    class Registered : SymbolRegistrationResult()
    class AlreadyRegistered : SymbolRegistrationResult()
    class AlreadyRegisteredTypeMismatch(val value: BaseSymbolValue) : SymbolRegistrationResult()
}
