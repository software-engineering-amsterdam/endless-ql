package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class TypeExpression extends AstNode {

    private Type type;
    private Expression expression;

    public TypeExpression(Type type) {
        this.type = type;
    }

    public TypeExpression(Type type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        type.acceptVisitor(visitor);

        if (expression != null) {
            expression.acceptVisitor(visitor);
        }

        visitor.visitTypeExpression(this);
    }
}
