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

    public Question(Token token, String label, Variable variable, Type nodeType, Expression value) {
        super(token);
        this.label = label;
        this.variable = variable;
        this.nodeType = nodeType;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public Variable getVariable() {
        return this.variable;
    }

    public Type getNodeType() {
        return this.nodeType;
    }

    public ASTNode getValue() {
        return this.value;
    }

    public Type getType() {
        return this.nodeType;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
