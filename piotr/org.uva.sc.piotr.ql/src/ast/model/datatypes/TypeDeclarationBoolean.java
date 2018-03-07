package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationBoolean extends TypeDeclaration {
    public TypeDeclarationBoolean(String identifier, Integer startLine, Integer endLine) {
        super(identifier, startLine, endLine);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
