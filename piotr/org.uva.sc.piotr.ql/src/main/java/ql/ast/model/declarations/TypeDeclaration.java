package ql.ast.model.declarations;

import ql.ast.model.ASTNode;

public abstract class TypeDeclaration extends ASTNode implements TypeDeclarationInterface {

    private String identifier;

    TypeDeclaration(String identifier, MetaInformation metaInformation) {
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
