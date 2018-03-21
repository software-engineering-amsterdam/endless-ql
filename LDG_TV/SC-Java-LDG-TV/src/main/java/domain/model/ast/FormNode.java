package domain.model.ast;

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
     * Add QuestionNode to ASTNodes list.
     * @param q
     */
    public void addQuestion(QuestionNode q){
        this.ASTNodes.add(q);
    }

    /**
     * Add ConditionNode to ASTNodes list
     * @param ifNode
     */
    public void addIfNode(ConditionNode ifNode){
        this.ASTNodes.add(ifNode);
        this.lastIfIndex = this.ASTNodes.size() - 1;
    }

    /**
     * Add QuestionNode to the last IfNode in ASTNodes list.
     * @param q QuestionNode to add
     */
    public void addToLastIf(QuestionNode q){
        ConditionNode ifNode = (ConditionNode) this.ASTNodes.get(this.lastIfIndex); // TODO check for instance of and not out of bounds
        ifNode.addQuestion(q);
    }
    /**
     * Add QuestionNode to the last IfNode else questions in ASTNodes list.
     * @param q QuestionNode to add
     */
    public void addToLastIfElse(QuestionNode q){
        ConditionNode ifNode = (ConditionNode) this.ASTNodes.get(this.lastIfIndex); // TODO check for instance of and not out of bounds
        ifNode.addElseQuestion(q);
    }

    /**
     * Get variable from the list of all QuestionNode's in ASTNodes list based on the variable identifier.
     * @param identifier Variable identifier to search for in list.
     * @return Found variable, if non found returns null.
     */
    public Variable getVariableFromList(String identifier){
        Variable qv = null ;
        for (QuestionNode qan : getAllQuestionASTNodes()) {
            if(qan.getVariable().getIdentifier().equals(identifier) ){
                qv = qan.getVariable();
            }
        }
        return qv;
    }

    /**
     * Get QuestionNode from the list of all QuestionNode's in ASTNodes list based on variable identifier.
     * @param identifier Variable identifier to search for in list.
     * @return Found QuestionNode, if non found returns null.
     */
    public QuestionNode getQuestionByVariableIdentifier(String identifier){
        QuestionNode qv = null ;
        for (QuestionNode qan : getAllQuestionASTNodes()) {
            if(qan.getVariable().getIdentifier().equals(identifier) ){
                return qan;
            }
        }
        return null;
    }

    /**
     * Returns a list of all the QuestionNode in the ASTNodes list, also loops over the QuestionNode's that are under and ConditionNode.
     * @return List of all QuestionNode's.
     */
    public List<QuestionNode> getAllQuestionASTNodes(){
        List<QuestionNode> temp = new ArrayList<>();
        for (ASTNode an : getASTNodes()) {
            if(an instanceof QuestionNode){
                temp.add((QuestionNode) an);
            }
            if(an instanceof ConditionNode){
                temp.addAll(((ConditionNode) an).getQuestionNodes());
                temp.addAll(((ConditionNode) an).getElseNodes());
            }
        }
        return temp;
    }

    /**
     * Evaluate if the conditions for all the IfASTNodes are satisfied.
     */
    public void evaluateIfs() {
        for (ASTNode an : getASTNodes()) {
            if(an instanceof ConditionNode){
                ConditionNode ifNode = (ConditionNode) an;
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
