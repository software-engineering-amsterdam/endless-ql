package domain.model;

import java.util.ArrayList;
import java.util.List;

public class IfNode extends Node {

    private List<QuestionNode> questionNodes;

    public IfNode() {
        this.questionNodes = new ArrayList<>();
    }

    public List<QuestionNode> getQuestionNodes() {
        return questionNodes;
    }

    public void addQuestion(QuestionNode q){
        this.questionNodes.add(q);
    }
}
