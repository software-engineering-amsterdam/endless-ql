package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Variable extends Expression {
    private final String variableName;

    private Question linkedQuestion = null;

    public Variable(final Token token, final String variableName) {
        super(token);
        this.variableName = variableName;
    }

    public final String getVariableName() {
        return this.variableName;
    }

    public final Question getLinkedQuestion() {
        return this.linkedQuestion;
    }

    public final void setLinkedQuestion(final Question linkedQuestion) {
        this.linkedQuestion = linkedQuestion;
    }

    public final Type getType() {
        if (this.linkedQuestion == null) {
            return new Type(NodeType.UNKNOWN);
        }

        return this.linkedQuestion.getType();
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
