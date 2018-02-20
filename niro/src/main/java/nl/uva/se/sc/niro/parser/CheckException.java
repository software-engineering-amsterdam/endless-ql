package nl.uva.se.sc.niro.parser;

public class CheckException extends RuntimeException {
    private String message;

    public CheckException(String message) {
        this.message = message;
    }
}
