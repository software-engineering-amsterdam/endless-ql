package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationString extends TypeDeclaration {
    public TypeDeclarationString(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
