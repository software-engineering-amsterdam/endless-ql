package ast.model.datatypes;

import ast.model.ASTNode;
import ast.visitors.ASTNodeVisitor;

public abstract class TypeDeclaration extends ASTNode {

    private String identifier;

    public TypeDeclaration(String identifier, Integer startLine, Integer endLine) {
        super(startLine, endLine);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
