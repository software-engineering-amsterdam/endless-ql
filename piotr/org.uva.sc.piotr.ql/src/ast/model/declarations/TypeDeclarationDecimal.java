package ast.model.declarations;

import ast.visitors.ASTNodeVisitor;
import types.DataType;

public class TypeDeclarationDecimal extends TypeDeclaration {
    public TypeDeclarationDecimal(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public DataType.Type toDataType() {
        return DataType.Type.DECIMAL;
    }
}
