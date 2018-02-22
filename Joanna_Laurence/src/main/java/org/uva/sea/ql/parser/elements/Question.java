package org.uva.sea.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.QLExprEvaluate;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.elements.types.Variable;
import org.uva.sea.ql.traverse.Visitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Question extends ASTNode {

    private String label;
    private Variable variable;
    private Type nodeType;
    private ASTNode value;

    public Question(Token token, String label, Variable variable, Type nodeType, ASTNode value) {
        super(token);
        this.label = label;
        this.variable = variable;
        this.nodeType = nodeType;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Variable getVariable() {
        return variable;
    }

    public Type getNodeType() {
        return nodeType;
    }

    /**
     * The value that is defined in QL
     * @return
     */
    public ASTNode getValue() {
        return this.value;
    }

    public Type getType() {
        return nodeType;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
