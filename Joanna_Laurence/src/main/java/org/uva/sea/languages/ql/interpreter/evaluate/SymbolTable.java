package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, Value> symbolTable = new HashMap<>();


    public void addOrUpdateValue(String name, Value val) {
        this.symbolTable.put(name, val);
    }

    public Value getValue(String name) {
        return this.symbolTable.get(name);
    }
}
