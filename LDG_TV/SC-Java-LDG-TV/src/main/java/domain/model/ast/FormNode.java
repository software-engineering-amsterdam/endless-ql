package domain.model.ast;

import domain.model.ast.IfASTNode;
import domain.model.ast.ASTNode;
import domain.model.ast.QuestionASTNode;
import domain.model.stylesheet.Stylesheet;
import domain.model.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class FormNode {

    private String formIdentifier;
    private List<ASTNode> ASTNodes;
    private List<Variable> referencedVariables;
    private Stylesheet stylesheet;
    private int lastIfIndex;

    public FormNode(){
        this.ASTNodes = new ArrayList<>();
        this.referencedVariables = new ArrayList<Variable>();
    }

    /**
     * Add QuestionASTNode to ASTNodes list.
     * @param q
     */
    public void addQuestion(QuestionASTNode q){
        this.ASTNodes.add(q);
    }

    /**
     * Add IfASTNode to ASTNodes list
     * @param ifNode
     */
    public void addIfNode(IfASTNode ifNode){
        this.ASTNodes.add(ifNode);
        this.lastIfIndex = this.ASTNodes.size() - 1;
    }

    /**
     * Add QuestionASTNode to the last IfNode in ASTNodes list.
     * @param q QuestionASTNode to add
     */
    public void addToLastIf(QuestionASTNode q){
        IfASTNode ifNode = (IfASTNode) this.ASTNodes.get(this.lastIfIndex); // TODO check for instance of and not out of bounds
        ifNode.addQuestion(q);
    }
    /**
     * Add QuestionASTNode to the last IfNode else questions in ASTNodes list.
     * @param q QuestionASTNode to add
     */
    public void addToLastIfElse(QuestionASTNode q){
        IfASTNode ifNode = (IfASTNode) this.ASTNodes.get(this.lastIfIndex); // TODO check for instance of and not out of bounds
        ifNode.addElseQuestion(q);
    }

    /**
     * Get variable from the list of all QuestionASTNode's in ASTNodes list based on the variable identifier.
     * @param identifier Variable identifier to search for in list.
     * @return Found variable, if non found returns null.
     */
    public Variable getVariableFromList(String identifier){
        Variable qv = null ;
        for (QuestionASTNode qan : getAllQuestionASTNodes()) {
            if(qan.getVariable().getIdentifier().equals(identifier) ){
                qv = qan.getVariable();
            }
        }
        return qv;
    }

    /**
     * Get QuestionASTNode from the list of all QuestionASTNode's in ASTNodes list based on variable identifier.
     * @param identifier Variable identifier to search for in list.
     * @return Found QuestionASTNode, if non found returns null.
     */
    public QuestionASTNode getQuestionByVariableIdentifier(String identifier){
        QuestionASTNode qv = null ;
        for (QuestionASTNode qan : getAllQuestionASTNodes()) {
            if(qan.getVariable().getIdentifier().equals(identifier) ){
                return qan;
            }
        }
        return null;
    }

    /**
     * Returns a list of all the QuestionASTNode in the ASTNodes list, also loops over the QuestionASTNode's that are under and IfASTNode.
     * @return List of all QuestionASTNode's.
     */
    public List<QuestionASTNode> getAllQuestionASTNodes(){
        List<QuestionASTNode> temp = new ArrayList<>();
        for (ASTNode an : getASTNodes()) {
            if(an instanceof QuestionASTNode){
                temp.add((QuestionASTNode) an);
            }
            if(an instanceof IfASTNode){
                temp.addAll(((IfASTNode) an).getQuestionNodes());
                temp.addAll(((IfASTNode) an).getElseNodes());
            }
        }
        return temp;
    }

    /**
     * Evaluate if the conditions for all the IfASTNodes are satisfied.
     */
    public void evaluateIfs() {
        for (ASTNode an : getASTNodes()) {
            if(an instanceof IfASTNode){
                IfASTNode ifNode = (IfASTNode) an;
                ifNode.checkConditions();
            }
        }
    }

    public List<Variable> getReferencedVariables() {
        return referencedVariables;
    }

    public void setFormIdentifier(String formIdentifier) {
        this.formIdentifier = formIdentifier;
    }
    public String getFormIdentifier() {
        return formIdentifier;
    }

    public List<ASTNode> getASTNodes() {
        return this.ASTNodes;
    }

    public Stylesheet getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
    }


    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        for (ASTNode n : ASTNodes) {
            str.append(n)
                    .append('\n');
        }

        return str.toString();
    }
}
