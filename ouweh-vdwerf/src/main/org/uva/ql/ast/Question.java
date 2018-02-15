package main.org.uva.ql.ast;

import main.org.uva.ql.ast.type.Type;

public class Question extends Statement {
    private String content;
    private Type type;

    public Question(String Content, Type Type) {
        this.content = Content;
        this.type = Type;
    }

}
