package domain.model;

import java.util.ArrayList;
import java.util.List;

public class IfASTNode extends ASTNode {

    private List<QuestionASTNode> questionNodes;

    public IfASTNode(boolean visible) {
        this.questionNodes = new ArrayList<>();
        this.setVisible(visible);
    }

    public List<QuestionASTNode> getQuestionNodes() {
        return questionNodes;
    }

    public void addQuestion(QuestionASTNode q){
        this.questionNodes.add(q);
    }
}
