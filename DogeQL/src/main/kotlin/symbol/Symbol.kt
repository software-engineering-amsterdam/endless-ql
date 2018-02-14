package symbol


enum class SymbolType {
    BOOLEAN,
    INTEGER
}

abstract class SymbolValue(var type: SymbolType)
class BooleanValue(var value: Boolean) : SymbolValue(SymbolType.BOOLEAN)
class IntegerValue(var value: Int) : SymbolValue(SymbolType.INTEGER)