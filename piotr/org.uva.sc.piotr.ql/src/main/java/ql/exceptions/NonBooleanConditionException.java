package ql.exceptions;

public class NonBooleanConditionException extends RuntimeException {
    public NonBooleanConditionException(String message) {
        super(message);
    }
}
