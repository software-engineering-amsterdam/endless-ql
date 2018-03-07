package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationDecimal extends TypeDeclaration {
    public TypeDeclarationDecimal(String identifier, Integer startLine, Integer endLine) {
        super(identifier, startLine, endLine);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
