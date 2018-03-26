package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Type;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Variable;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;


public class Question extends Statement {

    private final String label;
    private final Variable variable;
    private final Type nodeType;
    private final Expression value;

    public Question(final Token token, final String label, final Variable variable, final Type nodeType, final Expression value) {
        super(token);
        this.label = label;
        this.variable = variable;
        this.nodeType = nodeType;
        this.value = value;
    }

    public final String getLabel() {
        return this.label;
    }

    public final Variable getVariable() {
        return this.variable;
    }

    public final Type getNodeType() {
        return this.nodeType;
    }

    public final ASTNode getValue() {
        return this.value;
    }

    public final Type getType() {
        return this.nodeType;
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
