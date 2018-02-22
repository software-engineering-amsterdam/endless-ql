package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.QLExprEvaluate;
import org.uva.sea.ql.parser.elements.types.Bool;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.elements.types.Var;
import org.uva.sea.ql.traverse.Traverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question extends ASTNode implements QuestionContainerNode {
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

    public List<Question> evalQuestions(QLExprEvaluate exprEvaluate) {
        return Collections.singletonList(this);
    }

    public Question() {
        System.out.println("Question created");
    }

    public String getLabel() {
        return label;
    }

    public Var getVariable() {
        return variable;
    }

    public Type getNodeType() {
        return nodeType;
    }

    public ASTNode getValue() {
        return value;
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
