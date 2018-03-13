package ast.model.declarations;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationMoney extends TypeDeclaration {
    public TypeDeclarationMoney(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
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
