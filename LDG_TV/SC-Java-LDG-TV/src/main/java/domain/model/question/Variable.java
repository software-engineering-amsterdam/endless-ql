package domain.model.question;

public abstract class Variable {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
