package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

import java.util.List;

public class Condition extends ASTNode {

    private ASTNode expression;
    private List<Question> questions;

    public Condition(ASTNode expression, List<Question> questions)
    {
        this.questions = questions;
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public void setExpression(ASTNode expression) {
        this.expression = expression;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void traverse(Traverse traverse, TraverseType traverseType) {
        traverse.doCondition(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.expression.traverse(traverse, traverseType);
        for (ASTNode node: this.questions) {
            node.traverse(traverse, traverseType);
        }
    }


    public Type getType() {
        return new Type("undefined");
    }
}
