package com.chariotit.uva.sc.qdsl.ast.ql.visitor;

public class TypeCheckError {

    String message;
    Integer lineNumber;
    Integer columnNumber;
    Level level;

    public TypeCheckError(String message, Integer lineNumber, Integer columnNumber) {
        this.message = message;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.level = Level.ERROR;
    }

    public TypeCheckError(String message, Integer lineNumber, Integer columnNumber, Level level) {
        this.message = message;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.level = level;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public enum Level {
        ERROR,
        WARN
    }
}
