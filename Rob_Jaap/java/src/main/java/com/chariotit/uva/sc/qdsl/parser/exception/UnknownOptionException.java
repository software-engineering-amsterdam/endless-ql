package com.chariotit.uva.sc.qdsl.parser.exception;

public class UnknownOptionException extends RuntimeException {

    public UnknownOptionException() {
        super(String.format(
                "Undefined option in %s in %s",
                Thread.currentThread().getStackTrace()[2].getClassName(),
                Thread.currentThread().getStackTrace()[2].getMethodName()
        ));
    }
}
