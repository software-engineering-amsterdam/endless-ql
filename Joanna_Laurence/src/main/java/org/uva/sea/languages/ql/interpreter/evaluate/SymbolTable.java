package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.util.HashMap;

public class SymbolTable {

    //string - name of the variable
    private HashMap<String, Value> symbolTable = new HashMap<>();

    /**
     * Add or update symbol table
     *
     * @param name Name of the symbol/variable
     * @param val  Value of the symbol
     */
    public void addOrUpdateValue(String name, Value val) {
        symbolTable.put(name, val);
    }

    /**
     * @param name Name of the symbol
     * @return Value of the symbol or null on error
     */
    public Value getValue(String name) {
        return symbolTable.get(name);
    }
}
