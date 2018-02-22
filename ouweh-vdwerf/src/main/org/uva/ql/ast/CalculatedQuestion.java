package org.uva.ql.ast;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.type.Type;

public class CalculatedQuestion extends Question {
    private Expression expression;

    public CalculatedQuestion(String name, String content, Type type, Expression expression) {
        super(name,content,type);
        this.expression = expression;
    }

    @Override
    public String toString() {
        String content = this.getContent();
        //return content;
        return String.format("%s\t%s:%s = %s", this.getContent(), this.getName(), this.getType(), this.expression);
    }
}
