package ql.ast.model.declarations;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class TypeDeclarationInteger extends TypeDeclaration {
    public TypeDeclarationInteger(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    public TypeDeclarationInteger(String identifier) {
        super(identifier);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression.DataType toDataType() {
        return Expression.DataType.INTEGER;
    }
}
