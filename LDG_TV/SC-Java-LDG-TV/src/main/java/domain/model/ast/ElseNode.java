package domain.model.ast;

import java.util.ArrayList;
import java.util.List;

public class ElseNode {

    private List<QuestionNode> questionNodes;

    public ElseNode() {
        this.questionNodes = new ArrayList<>();
    }

    /**
     * Adds a QuestionNode to the questionNodes list.
     *
     * @param q QuestionNode to add
     */
    public void addQuestion(QuestionNode q) {
        this.questionNodes.add(q);
    }

    public List<QuestionNode> getQuestionNodes() {
        return questionNodes;
    }
}