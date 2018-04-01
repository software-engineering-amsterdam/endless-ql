package ql.error;

public class Error {

    public enum Level {
        CRITICAL,
        WARNING
    }

    private final Level level;
    private final String message;

    public Error(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Level getLevel() {
        return level;
    }
}
