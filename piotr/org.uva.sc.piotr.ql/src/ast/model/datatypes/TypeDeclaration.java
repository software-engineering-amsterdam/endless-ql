package ast.model.datatypes;

public abstract class TypeDeclaration {

    private String identifier;

    public TypeDeclaration(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
