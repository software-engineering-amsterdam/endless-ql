package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;

import java.util.HashSet;
import java.util.Set;

public abstract class Constant extends Expression {

    public Constant(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public Set<String> getPrerequisites() {
        return new HashSet<>();
    }

    @Override
    public void evaluate(SymbolTable symbolTable) {
        // Constants have their expression value always set. No need to evaluate further
    }
}
