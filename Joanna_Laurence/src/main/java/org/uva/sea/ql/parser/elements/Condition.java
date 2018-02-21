package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.QLExprEvaluate;
import org.uva.sea.ql.parser.elements.types.Bool;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

import java.util.ArrayList;
import java.util.List;

public class Condition extends ASTNode implements QuestionContainerNode {

    private ASTNode expression;
    private List<Question> questions;

    public Condition(ASTNode expression, List<Question> questions)
    {
        this.questions = questions;
        this.expression = expression;
    }

    public List<Question> evalQuestions(QLExprEvaluate exprEvaluate) {
        Bool conditionValue = (Bool)exprEvaluate.getValue(expression);
        if(conditionValue.isTrue()) {
            return questions;
        }
        return new ArrayList<>();
    }

    public ASTNode getExpression() {
        return expression;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doCondition(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.expression.traverseNode(traverse, traverseType);
        expression.doTraversal(traverse, traverseType);
        for (ASTNode node: this.questions) {
            node.doTraversal(traverse, traverseType);
        }
    }

    public Type getType() {
        return new Type("undefined");
    }
}
