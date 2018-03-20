package exceptions;

public class DuplicateDeclarationException extends RuntimeException {
    public DuplicateDeclarationException(String message) {
        super(message);
    }
}
