package exceptions;

public class NonBooleanConditionException extends Exception {
    public NonBooleanConditionException(String message) {
        super(message);
    }
}
