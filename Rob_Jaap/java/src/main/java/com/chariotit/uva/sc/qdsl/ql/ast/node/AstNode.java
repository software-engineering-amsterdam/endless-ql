package com.chariotit.uva.sc.qdsl.ql.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public abstract class AstNode {

    private Integer lineNumber;
    private Integer columnNumber;

    public AstNode(Integer lineNumber, Integer columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public abstract void acceptVisitor(NodeVisitor visitor);
}

