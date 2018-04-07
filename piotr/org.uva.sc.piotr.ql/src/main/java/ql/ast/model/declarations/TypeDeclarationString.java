package ql.ast.model.declarations;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class TypeDeclarationString extends TypeDeclaration {
    public TypeDeclarationString(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression.DataType toDataType() {
        return Expression.DataType.STRING;
    }
}
