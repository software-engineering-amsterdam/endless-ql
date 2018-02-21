package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.Evaluator;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QLEvaluator {

    private QLExprEvaluate exprEvaluate;

    public QLEvaluator(Map<NodeType, Evaluator> evaluator) {
        this.exprEvaluate = new QLExprEvaluate(evaluator);
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
            questionList.addAll(((QuestionContainerNode)node).evalQuestions(this.exprEvaluate));
        }

        return questionList;
    }
}
