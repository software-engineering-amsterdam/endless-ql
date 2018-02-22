package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.QLExprEvaluate;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.elements.types.Var;
import org.uva.sea.ql.traverse.BaseVisitor;

import java.util.*;

public class Question extends ASTNode implements QuestionContainerNode {
    private String label;
    private Var variable;
    private Type nodeType;
    private ASTNode value;
    private ASTNode computedAnswer;


    public Question(String label, Var variable, Type nodeType, ASTNode value) {
        this.label = label;
        this.variable = variable;
        this.nodeType = nodeType;
        this.value = value;
    }

    public List<Question> evalQuestions(QLExprEvaluate exprEvaluate, HashMap<String, ASTNode> symbolTable) {
        this.computedAnswer = computeAnswer(exprEvaluate, symbolTable);
        return Collections.singletonList(this);
    }

    private ASTNode computeAnswer(QLExprEvaluate exprEvaluate, HashMap<String, ASTNode> symbolTable) {
        return (value != null) ? exprEvaluate.getValue(this.value) : symbolTable.get(this.variable.getVariableName());
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
        return this.computedAnswer;
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return nodeType;
    }
}
