package ql.ast.model.declarations;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class TypeDeclarationDecimal extends TypeDeclaration {
    public TypeDeclarationDecimal(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    public TypeDeclarationDecimal(String identifier) {
        super(identifier);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression.DataType toDataType() {
        return Expression.DataType.DECIMAL;
    }
}
