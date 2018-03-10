package ast.model.declarations;

import ast.model.ASTNode;
import types.DataType;

public abstract class TypeDeclaration extends ASTNode implements TypeDeclarationInterface {

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
