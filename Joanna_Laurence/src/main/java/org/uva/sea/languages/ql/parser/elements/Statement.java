package org.uva.sea.languages.ql.parser.elements;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Statement extends ASTNode {
    private Question question;
    private IfStatement ifStatement;

    public Question getQuestion() {
        return question;
    }

    public IfStatement getIfStatement() {
        return ifStatement;
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
