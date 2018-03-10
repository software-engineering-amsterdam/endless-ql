package ast.model.expressions.values;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;
import types.DataType;

public class Literal extends Expression {

    private String value;
    private DataType.Type type;

    public Literal(String value, DataType.Type type, MetaInformation metaInformation) {
        super(metaInformation);
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataType.Type getType() {
        return type;
    }

    public void setType(DataType.Type type) {
        this.type = type;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
