package domain.model.ast;

import java.util.ArrayList;
import java.util.List;

public class ASTFormNode {
    private List<QuestionNode> questions;

    public ASTFormNode() {
        this.questions = new ArrayList<>();
    }

    public List<QuestionNode> getQuestions() {
        return questions;
    }

    public void addQuestionNode(QuestionNode questionNode){
        this.questions.add(questionNode);
    }
}
