package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.QLExprEvaluate;
import org.uva.sea.ql.parser.elements.types.Bool;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Condition extends ASTNode implements QuestionContainerNode {

    private ASTNode expression;
    private Statements statements;

    public Condition(ASTNode expression, Statements statements)
    {
        this.statements = statements;
        this.expression = expression;
    }

    /**
     *
     * @param exprEvaluate
     * @return
     */
    public List<Question> evalQuestions(QLExprEvaluate exprEvaluate, HashMap<String, ASTNode> symbolTable) {
        List<Question> questions = new ArrayList<>();
        Bool conditionValue = (Bool)exprEvaluate.getValue(expression);
        if(exprEvaluate.isNotComplete() )
            return new ArrayList<>();

        if(conditionValue.isTrue()) {
            for(ASTNode node : this.statements.getStatementList()) {
                questions.addAll(((QuestionContainerNode)node).evalQuestions(exprEvaluate,symbolTable));
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
