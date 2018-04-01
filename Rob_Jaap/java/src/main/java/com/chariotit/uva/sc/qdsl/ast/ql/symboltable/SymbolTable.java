package com.chariotit.uva.sc.qdsl.ast.ql.symboltable;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.exception.DuplicateSymbolMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

    private HashMap<String, SymbolTableEntry> entries = new HashMap<>();

    public void addEntry(SymbolTableEntry entry) throws DuplicateSymbolMismatchException {
        if (hasEntry(entry.getLabel()) && entries.get(entry.getLabel())
                .getExpressionType() != entry.getExpressionType()) {
            throw new DuplicateSymbolMismatchException(entries.get(entry.getLabel()), entry);
        } else {
            entries.put(entry.getLabel(), entry);
        }
    }

    public Set<Map.Entry<String, SymbolTableEntry>> getEntries() {
        return entries.entrySet();
    }

    public SymbolTableEntry getEntry(String key) {
        return entries.get(key);
    }

    public boolean hasEntry(String key) {
        return getEntry(key) != null;
    }
}
