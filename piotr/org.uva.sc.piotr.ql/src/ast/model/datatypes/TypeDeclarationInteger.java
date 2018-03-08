package ast.model.datatypes;

import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationInteger extends TypeDeclaration {
    public TypeDeclarationInteger(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }}
