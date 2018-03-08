package com.chariotit.uva.sc.qdsl.ast.visitor;

public class TypeCheckError {

    String message;
    Integer lineNumber;
    Integer columnNumber;

    public TypeCheckError(String message, Integer lineNumber, Integer columnNumber) {
        this.message = message;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
