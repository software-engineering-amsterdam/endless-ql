package domain.model.question;

public class StringVariable extends Variable {
    private final String value;

    public StringVariable(String name, String value) {
        super(name);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + ": String";
    }
}
