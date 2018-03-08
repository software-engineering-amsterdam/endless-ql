package domain;

import domain.model.IfNode;
import domain.model.Node;
import domain.model.QuestionNode;

import java.util.ArrayList;
import java.util.List;

public class FormNode {

    private String formIdentifier;
    private FormData formData;
    private List<Node> nodes;
    private int lastIfIndex;

    public FormNode(){
        this.formData = new FormData();
        this.nodes = new ArrayList<>();
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

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void addQuestion(QuestionNode q){
        this.nodes.add(q);
    }

    public void addIfNode(IfNode ifNode){
        this.nodes.add(ifNode);
        this.lastIfIndex = this.nodes.size() - 1;
    }

    public void addToLastIf(QuestionNode q){
        IfNode ifNode = (IfNode) this.nodes.get(this.lastIfIndex); // TODO check for instance of and not out of bounds
        ifNode.addQuestion(q);
    }
}
