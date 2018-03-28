package ql.typechecker.messages.error;

public class Error {
    private final String error;

    Error(String error) {
        this.error = error;
    }

    public String message() {
        return error;
    }
}
