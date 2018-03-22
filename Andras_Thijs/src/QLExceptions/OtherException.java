package QLExceptions;

/**
 * A basic Exception for all other scenarios (So no type or syntax exception)
 */
public class OtherException extends Exception {
    private String message;

    public OtherException() {}

    public OtherException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
