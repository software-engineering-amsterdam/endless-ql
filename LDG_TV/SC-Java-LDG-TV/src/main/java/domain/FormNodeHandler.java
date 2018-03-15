package domain;

import domain.model.ASTNode;
import domain.model.IfASTNode;
import domain.model.QuestionASTNode;

import java.util.ArrayList;
import java.util.List;

public class FormNodeHandler {
    private FormNode formNode;

    public FormNodeHandler(FormNode formNode) {
        this.formNode = formNode;
    }

    public List<QuestionASTNode> getAllVisibleQuestions(){

        List<QuestionASTNode> visQuestion = new ArrayList<>();
        for(ASTNode n : this.formNode.getASTNodes()){
            if(!n.isVisible()) {
                continue;
            }

            if(n instanceof QuestionASTNode){
                visQuestion.add((QuestionASTNode) n);
                continue;
            }

            IfASTNode ifASTNode = (IfASTNode) n;
            visQuestion.addAll(ifASTNode.getQuestionNodes());
        }

        return visQuestion;

    }
}
