package org.uva.sea.ql.evaluate;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Value> symbolTable = new HashMap<>();

    /**
     * Add or update symbol table
     *
     * @param name Name of the symbol
     * @param val  Value of the symbol
     */
    public void addOrUpdateValue(String name, Value val) {
        symbolTable.put(name, val);
    }

    /**
     * @param name Name of the symbol
     * @return Value of the symbol or null on error
     */
    public void getValue(String name) {
        symbolTable.get(name);
    }
}
