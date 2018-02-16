package main.org.uva.ql.ast;

import main.org.uva.ql.ast.type.Type;

public class Question extends Statement {
    private String name;
    private String content;
    private Type type;

    public Question(String name, String content, Type type) {
        this.name = name;
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s:%s",this.content, this.name, this.type);
    }
}
