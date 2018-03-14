package data.symbol

sealed class SymbolRegistrationResult {
    class Registered(val symbol: Symbol) : SymbolRegistrationResult()
    class AlreadyRegistered : SymbolRegistrationResult()
    class AlreadyRegisteredTypeMismatch(val previous: Symbol) : SymbolRegistrationResult()
}
