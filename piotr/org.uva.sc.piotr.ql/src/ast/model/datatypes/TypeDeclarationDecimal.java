package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationDecimal extends TypeDeclaration {
    public TypeDeclarationDecimal(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
