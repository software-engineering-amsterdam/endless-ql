package models.ast.elements.datatypes;

public abstract class DataType {

    private TypeString identifier;

    public TypeString getIdentifier() {
        return identifier;
    }

    public void setIdentifier(TypeString identifier) {
        this.identifier = identifier;
    }
}
