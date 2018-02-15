package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.Evaluator;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.types.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QLEvaluator {

    private QLExprEvaluate exprEvaluate;

    public QLEvaluator(Map<String, Evaluator> evaluator) {
        this.exprEvaluate = new QLExprEvaluate(evaluator);
    }

    private List<Question> getConditionQuestions(Condition node) {
        Bool conditionValue = (Bool)this.exprEvaluate.getValue(node.getExpression());
        if(conditionValue.isTrue()) {
            return node.getQuestions();
        }
        return new ArrayList<>();
    }

    /**
     * Get questions from form
     * @param form The Form node
     * @return List of questions
     */
    public List<Question> getQuestions(Form form) {
        List<Question> questionList = new ArrayList<>();

        List<ASTNode> nodes = form.getStatements().getStatementList();
        for(ASTNode node : nodes) {
            if(node instanceof Question) {
                questionList.add((Question)node);
            } else if (node instanceof Condition) {
                questionList.addAll(getConditionQuestions((Condition)node));
            }
        }

        return questionList;
    }
}
