package domain;

import domain.model.QuestionNode;
import domain.model.variable.Variable;

import java.util.*;

public class FormData {
    private Map<List<Variable>, List<QuestionNode>> questions;
    private List<Variable> referencedVariables;

    public FormData(){
        this.questions = new HashMap<List<Variable>, List<QuestionNode>>();
        this.referencedVariables = new ArrayList<Variable>();
    }

    public Map<List<Variable>, List<QuestionNode>> getQuestions(){
        return this.questions;
    }

    public void addConditionsAsKey(List<Variable> conditions){
        if(!this.questions.containsKey(conditions)){
            this.questions.put(conditions, new ArrayList<QuestionNode>());
        };
    }
    public void addQuestion(List<Variable> conditions, QuestionNode questionNodeStructure){
        this.questions.get(conditions).add(questionNodeStructure); ;
    }

    public List<Variable> getReferencedVariables() {
        return referencedVariables;
    }
    public List<QuestionNode> getAllQuestions(){
        List<QuestionNode> allQuestionNodes = new ArrayList<QuestionNode>();
        for (Map.Entry<List<Variable>, List<QuestionNode>> entry : this.getQuestions().entrySet()) {
            for (QuestionNode qs : entry.getValue()) {
                allQuestionNodes.add(qs);
            }
        }
        return allQuestionNodes;
    }
    public Variable getVariableByLabel(String label){
        Variable qv = null ;
        for (Map.Entry<List<Variable>, List<QuestionNode>> entry : this.getQuestions().entrySet()) {
            for (QuestionNode qs : entry.getValue()) {
                if (qs.getVariable().getIdentifier().equals(label)){
                    qv = qs.getVariable();
                }
            }
        }
        return qv;
    }
}
