package ast.model.expressions.values;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class Literal extends Expression {

    public enum Type {
        STRING,
        BOOLEAN,
        INTEGER,
        DECIMAL
    }

    private String value;
    private Type type;

    public Literal(String value, Type type, Integer startLine, Integer endLine) {
        super(startLine, endLine);
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
