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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }
}
