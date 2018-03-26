package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Parameter extends QLSNode {

    private final String parameter;

    public Parameter(final Token token, final String parameter) {
        super(token);
        this.parameter = parameter;
    }

    public final String getParameter() {
        return this.parameter;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
