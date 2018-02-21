package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.QLExprEvaluate;
import org.uva.sea.ql.parser.elements.types.Bool;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

import java.util.ArrayList;
import java.util.List;

public class Condition extends ASTNode implements QuestionContainerNode {

    private ASTNode expression;
    private Statements statements;

    public Condition(ASTNode expression, Statements statements)
    {
        this.statements = statements;
        this.expression = expression;
    }

    public List<Question> evalQuestions(QLExprEvaluate exprEvaluate) {
        List<Question> questions = new ArrayList<>();
        Bool conditionValue = (Bool)exprEvaluate.getValue(expression);
        if(conditionValue.isTrue()) {
            for(ASTNode node : this.statements.getStatementList()) {
                questions.addAll(((QuestionContainerNode)node).evalQuestions(exprEvaluate));
            }
        }
        return questions;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public Statements getStatements() {
        return statements;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doCondition(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        this.expression.traverseNode(traverse, traverseType);
        expression.doTraversal(traverse, traverseType);
        for (ASTNode node: this.statements.getStatementList()) {
            node.doTraversal(traverse, traverseType);
        }
    }

    public Type getType() {
        return new Type("undefined");
    }
}
