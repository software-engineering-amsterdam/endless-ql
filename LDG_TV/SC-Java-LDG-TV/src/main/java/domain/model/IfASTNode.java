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

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("if {\n");
        for (QuestionASTNode qn : questionNodes){
            str.append('\t')
                    .append(qn.toString())
                    .append('\n');
        }

        str.append('}');

        return str.toString();
    }
}
