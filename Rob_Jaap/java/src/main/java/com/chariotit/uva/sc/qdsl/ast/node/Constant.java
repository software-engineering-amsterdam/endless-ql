package com.chariotit.uva.sc.qdsl.ast.node;

public abstract class Constant extends Expression {

    public Constant(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

}
