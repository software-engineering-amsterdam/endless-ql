package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.elements.types.Var;
import org.uva.sea.ql.traverse.Traverse;

public class Question implements ASTNode {
    private String label;
    private Var variable;
    private Type nodeType;
    private ASTNode value;

    public Question(String label, Var variable, Type nodeType, ASTNode value) {
        this.label = label;
        this.variable = variable;
        this.nodeType = nodeType;
        this.value = value;
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

    public Type getNodeType() {
        return nodeType;
    }

    public void setNodeType(Type nodeType) {
        this.nodeType = nodeType;
    }

    public ASTNode getValue() {
        return value;
    }

    public void setValue(ASTNode value) {
        this.value = value;
    }

    public void traverse(Traverse traverse) {
        traverse.doQuestion(this);
        this.variable.traverse(traverse);
        this.nodeType.traverse(traverse);

        if(this.value != null)
            this.value.traverse(traverse);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
