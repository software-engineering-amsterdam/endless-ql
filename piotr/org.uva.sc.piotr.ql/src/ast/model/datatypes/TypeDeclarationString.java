package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationString extends TypeDeclaration {
    public TypeDeclarationString(String identifier, Integer startLine, Integer endLine) {
        super(identifier, startLine, endLine);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
