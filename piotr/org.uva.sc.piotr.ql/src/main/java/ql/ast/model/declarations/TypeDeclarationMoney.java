package ql.ast.model.declarations;

import ql.ast.model.expressions.Expression;
import ql.ast.visitors.ASTNodeVisitor;

public class TypeDeclarationMoney extends TypeDeclaration {
    public TypeDeclarationMoney(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    public TypeDeclarationMoney(String identifier) {
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
