package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Parameter extends QLSNode {

    private String parameter;

    public Parameter(Token token, String parameter) {
        super(token);
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
