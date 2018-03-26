package org.uva.sea.languages.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class SingleNode extends Expression {
    private final Expression value;

    protected SingleNode(final Token token, final Expression value) {
        super(token);
        this.value = value;
    }

    public final Expression getValue() {
        return this.value;
    }

    @Override
    public <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
