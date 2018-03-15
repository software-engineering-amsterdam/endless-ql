package com.chariotit.uva.sc.qdsl.ast.ql.symboltable.exception;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;

public class DuplicateSymbolMismatchException extends Exception {

    public DuplicateSymbolMismatchException(SymbolTableEntry entryA, SymbolTableEntry entryB) {
        super(
                String.format("Duplicate symbol declaration %s on lines %d and %d with different " +
                                "types (%s and %s)",
                        entryA.getLabel(),
                        entryA.getNode().getLineNumber(),
                        entryB.getNode().getLineNumber(),
                        entryA.getExpressionType(),
                        entryB.getExpressionType())
        );
    }
}
