package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Statement extends ASTNode {
    private Question question;
    private Condition condition;

    public Question getQuestion() {
        return question;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
