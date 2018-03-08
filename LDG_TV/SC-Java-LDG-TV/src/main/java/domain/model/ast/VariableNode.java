package domain.model.ast;

public class VariableNode {
    private String identifier;
    private String value;

    public VariableNode(String identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
