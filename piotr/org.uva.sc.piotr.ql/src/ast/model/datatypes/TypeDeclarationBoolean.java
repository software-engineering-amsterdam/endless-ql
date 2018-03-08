package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationBoolean extends TypeDeclaration {
    public TypeDeclarationBoolean(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
