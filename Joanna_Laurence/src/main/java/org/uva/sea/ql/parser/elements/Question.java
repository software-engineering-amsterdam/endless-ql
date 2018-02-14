package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.elements.types.Var;
import org.uva.sea.ql.traverse.Traverse;

public class Question implements ASTNode {
    private String label;
    private Var variable;
    private Type type;
    private ASTNode value;

    public Question(String label, Var variable, Type type, ASTNode value) {
        this.label = label;
        this.variable = variable;
        this.type = type;
    }

    public Question() {
        System.out.println("Question created");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Var getVariable() {
        return variable;
    }

    public void setVariable(Var variable) {
        this.variable = variable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ASTNode getValue() {
        return value;
    }

    public void setValue(ASTNode value) {
        this.value = value;
    }

    public void traverse(Traverse traverse) {
        traverse.doQuestion(this);
    }
}
