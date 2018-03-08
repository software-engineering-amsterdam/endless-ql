package com.chariotit.uva.sc.qdsl.ast.symboltable.exception;

import com.chariotit.uva.sc.qdsl.ast.symboltable.SymbolTableEntry;

public class DuplicateSymbolException extends Exception {

    public DuplicateSymbolException(SymbolTableEntry entryA, SymbolTableEntry entryB) {
        super(
                String.format("Duplicate symbol declaration %s on lines %d and %d",
                        entryA.getLabel(),
                        entryA.getNode().getLineNumber(),
                        entryB.getNode().getLineNumber())
        );
    }
}
