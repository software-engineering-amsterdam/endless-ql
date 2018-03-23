package com.chariotit.uva.sc.qdsl.ast.common;

public class TypeCheckError {

    private String message;
    private SourceFilePosition sourceFilePosition;
    private Level level;

    public enum Level {
        ERROR,
        WARN
    }

    public TypeCheckError(String message, SourceFilePosition filePosition) {
        this.message = message;
        this.sourceFilePosition = filePosition;
        this.level = Level.ERROR;
    }

    public TypeCheckError(String message, SourceFilePosition filePosition, Level level) {
        this.message = message;
        this.sourceFilePosition = filePosition;
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public SourceFilePosition getSourceFilePosition() {
        return sourceFilePosition;
    }

    public Level getLevel() {
        return level;
    }

}
