package domain.model.question;

public class BooleanVariable extends Variable {

    private final Boolean value;

    public BooleanVariable(String text, Boolean value) {
        super(text);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + ": boolean";
    }
}
