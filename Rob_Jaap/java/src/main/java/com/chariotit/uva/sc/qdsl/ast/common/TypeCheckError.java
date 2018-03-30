package com.chariotit.uva.sc.qdsl.ast.common;

import java.util.List;

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

    public static void print(TypeCheckError error) {
        System.out.println(String.format(
                "%4s line %3d, column %3d: %s",
                error.getLevel(),
                error.getSourceFilePosition().getLineNumber(),
                error.getSourceFilePosition().getColumnNumber(),
                error.getMessage()
        ));
    }

    public static void print(List<TypeCheckError> errors) {
        for (TypeCheckError error : errors) {
            print(error);
        }
    }

    public static boolean listContainsLevel(List<TypeCheckError> errors, TypeCheckError.Level level) {
        for (TypeCheckError error : errors) {
            if (error.level == level) {
                return true;
            }
        }

        return false;
    }


}
