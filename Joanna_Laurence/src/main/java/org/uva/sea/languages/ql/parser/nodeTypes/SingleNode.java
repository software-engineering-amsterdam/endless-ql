package org.uva.sea.languages.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class SingleNode extends Expression {
    private final Expression value;

    public SingleNode(Token token, Expression value) {
        super(token);
        this.value = value;
    }

    public Expression getValue() {
        return this.value;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
