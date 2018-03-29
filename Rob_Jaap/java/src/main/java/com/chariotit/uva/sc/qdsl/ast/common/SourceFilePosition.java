package com.chariotit.uva.sc.qdsl.ast.common;

public class SourceFilePosition {
    private final Integer lineNumber;
    private final Integer columnNumber;

    public SourceFilePosition(Integer lineNumber, Integer columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }
}
