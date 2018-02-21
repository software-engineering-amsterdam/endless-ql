package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.elements.types.Var;
import org.uva.sea.ql.traverse.Traverse;

public class Question extends ASTNode {
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

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doQuestion(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.variable.doTraversal(traverse,traverseType);
        this.nodeType.doTraversal(traverse,traverseType);

        if(this.value != null)
            this.value.doTraversal(traverse,traverseType);
    }

    public Type getType() {
        return nodeType;
    }
}
