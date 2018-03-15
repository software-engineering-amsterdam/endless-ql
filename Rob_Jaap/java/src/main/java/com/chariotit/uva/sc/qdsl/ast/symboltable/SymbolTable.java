package com.chariotit.uva.sc.qdsl.ast.symboltable;

import com.chariotit.uva.sc.qdsl.ast.symboltable.exception.DuplicateSymbolMismatchException;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, SymbolTableEntry> entries = new HashMap<>();

    public void addEntry(SymbolTableEntry entry) throws DuplicateSymbolMismatchException {
        if (entries.containsKey(entry.getLabel()) && entries.get(entry.getLabel())
                .getExpressionType() != entry.getExpressionType()) {
            throw new DuplicateSymbolMismatchException(entries.get(entry.getLabel()), entry);
        } else {
            entries.put(entry.getLabel(), entry);
        }
    }

    public SymbolTableEntry getEntry(String key) {
        return entries.get(key);
    }
}
