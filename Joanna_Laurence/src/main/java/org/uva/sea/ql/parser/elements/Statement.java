package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

public class Statement extends ASTNode {
    private Question question;
    private Condition condition;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void traverse(Traverse traverse, TraverseType traverseType) {
        traverse.doStatement(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.question.traverse(traverse,traverseType);
        this.condition.traverse(traverse,traverseType);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
