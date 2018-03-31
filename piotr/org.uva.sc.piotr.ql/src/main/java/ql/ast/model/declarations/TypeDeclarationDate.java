package ql.ast.model.declarations;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class TypeDeclarationDate extends TypeDeclaration {
    public TypeDeclarationDate(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    public TypeDeclarationDate(String identifier) {
        super(identifier);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression.DataType toDataType() {
        return Expression.DataType.DATE;
    }
}
