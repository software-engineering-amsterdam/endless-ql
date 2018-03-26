package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Variable extends Expression {
    private final String variableName;

    private Question linkedQuestion = null;

    public Variable(Token token, String variableName) {
        super(token);
        this.variableName = variableName;
    }

    public String getVariableName() {
        return this.variableName;
    }

    public Question getLinkedQuestion() {
        return this.linkedQuestion;
    }

    public void setLinkedQuestion(Question linkedQuestion) {
        this.linkedQuestion = linkedQuestion;
    }

    public Type getType() {
        if (this.linkedQuestion == null) {
            return new Type(NodeType.UNKNOWN);
        }

        return this.linkedQuestion.getType();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
