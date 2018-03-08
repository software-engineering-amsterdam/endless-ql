package domain;

import domain.model.QuestionASTNode;
import domain.model.variable.Variable;

import java.util.*;

public class FormData {
    private Map<List<Variable>, List<QuestionASTNode>> questions;
    private List<Variable> referencedVariables;

    public FormData(){
        this.questions = new HashMap<List<Variable>, List<QuestionASTNode>>();
        this.referencedVariables = new ArrayList<Variable>();
    }

    public Map<List<Variable>, List<QuestionASTNode>> getQuestions(){
        return this.questions;
    }

    public void addConditionsAsKey(List<Variable> conditions){
        if(!this.questions.containsKey(conditions)){
            this.questions.put(conditions, new ArrayList<QuestionASTNode>());
        };
    }
    public void addQuestion(List<Variable> conditions, QuestionASTNode questionNodeStructure){
        this.questions.get(conditions).add(questionNodeStructure); ;
    }

    public List<Variable> getReferencedVariables() {
        return referencedVariables;
    }
    public List<QuestionASTNode> getAllQuestions(){
        List<QuestionASTNode> allQuestionNodes = new ArrayList<QuestionASTNode>();
        for (Map.Entry<List<Variable>, List<QuestionASTNode>> entry : this.getQuestions().entrySet()) {
            for (QuestionASTNode qs : entry.getValue()) {
                allQuestionNodes.add(qs);
            }
        }
        return allQuestionNodes;
    }
    public Variable getVariableByLabel(String label){
        Variable qv = null ;
        for (Map.Entry<List<Variable>, List<QuestionASTNode>> entry : this.getQuestions().entrySet()) {
            for (QuestionASTNode qs : entry.getValue()) {
                if (qs.getVariable().getIdentifier().equals(label)){
                    qv = qs.getVariable();
                }
            }
        }
        return qv;
    }
}
