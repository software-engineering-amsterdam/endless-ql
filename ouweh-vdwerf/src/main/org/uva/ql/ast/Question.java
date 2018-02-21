package org.uva.ql.ast;

import org.uva.ql.ast.type.Type;
import org.uva.ql.visitor.StatementVisitor;

public class Question extends Statement {
    private String name;
    private String content;
    private Type type;

    public Question(String name, String content, Type type) {
        this.name = name;
        this.content = content;
        this.type = type;
    }

    public String getName () {
        return this.name;
    }

    public String getContent () {
        return this.content;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return String.format("%s\t%s:%s",this.content, this.name, this.type);
    }
}
