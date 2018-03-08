package ast.model.datatypes;

import ast.model.ASTNode;

public abstract class TypeDeclaration extends ASTNode {

    private String identifier;

    public TypeDeclaration(String identifier, MetaInformation metaInformation) {
        super(metaInformation);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
