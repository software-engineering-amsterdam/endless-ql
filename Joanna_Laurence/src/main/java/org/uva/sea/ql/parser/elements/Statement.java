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

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doStatement(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.question.doTraversal(traverse,traverseType);
        this.condition.doTraversal(traverse,traverseType);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
