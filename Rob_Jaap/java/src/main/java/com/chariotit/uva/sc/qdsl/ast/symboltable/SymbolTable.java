package com.chariotit.uva.sc.qdsl.ast.symboltable;

import com.chariotit.uva.sc.qdsl.ast.symboltable.exception.DuplicateSymbolException;

import java.util.HashMap;

public class SymbolTable {

    HashMap<String, SymbolTableEntry> entries = new HashMap<>();

    public void addEntry(SymbolTableEntry entry) throws DuplicateSymbolException {
        if (entries.containsKey(entry.getLabel())) {
            throw new DuplicateSymbolException(entries.get(entry.getLabel()), entry);
        } else {
            entries.put(entry.getLabel(), entry);
        }
    }

    public SymbolTableEntry getEntry(String key) {
        return entries.get(key);
    }
}
