package com.chariotit.uva.sc.qdsl.ast.ql.node;

import java.util.HashSet;
import java.util.Set;

public abstract class Constant extends Expression {

    public Constant(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public Set<String> getPrerequisites() {
        return new HashSet<>();
    }
}
