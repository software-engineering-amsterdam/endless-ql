package domain;

import domain.model.IfASTNode;
import domain.model.ASTNode;
import domain.model.QuestionASTNode;

import java.util.ArrayList;
import java.util.List;

public class FormNode {

    private String formIdentifier;
    private FormData formData;
    private List<ASTNode> ASTNodes;
    private int lastIfIndex;

    public FormNode(){
        this.formData = new FormData();
        this.ASTNodes = new ArrayList<>();
    }

    public void setFormIdentifier(String formIdentifier) {
        this.formIdentifier = formIdentifier;
    }
    public String getFormIdentifier() {
        return formIdentifier;
    }
    public FormData getFormData() {
        return this.formData;
    }

    public List<ASTNode> getASTNodes() {
        return this.ASTNodes;
    }

    public void addQuestion(QuestionASTNode q){
        this.ASTNodes.add(q);
    }

    public void addIfNode(IfASTNode ifNode){
        this.ASTNodes.add(ifNode);
        this.lastIfIndex = this.ASTNodes.size() - 1;
    }

    public void addToLastIf(QuestionASTNode q){
        IfASTNode ifNode = (IfASTNode) this.ASTNodes.get(this.lastIfIndex); // TODO check for instance of and not out of bounds
        ifNode.addQuestion(q);
    }
}
