package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationInteger extends TypeDeclaration {
    public TypeDeclarationInteger(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
