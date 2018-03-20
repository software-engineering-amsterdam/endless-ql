package exceptions;

public class DuplicateLabelException extends RuntimeException {
    public DuplicateLabelException(String message) {
        super(message);
    }
}
