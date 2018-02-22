package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Visitor;

public class Statement extends ASTNode {
    private Question question;
    private Condition condition;

    public Question getQuestion() {
        return question;
    }

    public Condition getCondition() {
        return condition;
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
