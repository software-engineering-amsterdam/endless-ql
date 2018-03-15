package com.chariotit.uva.sc.qdsl.ql.ast.node;

public abstract class Constant extends Expression {

    public Constant(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

}
