package domain;

import domain.model.question.Condition;
import domain.model.question.Question;
import domain.model.question.Variable;

import java.util.*;

public class FormData {
    private Map<List<Variable>, List<Question>> questions;
    private List<Variable> referencedVariables;

    public FormData(){
        this.questions = new HashMap<List<Variable>, List<Question>>();
        this.referencedVariables = new ArrayList<Variable>();
    }

    public Map<List<Variable>, List<Question>> getQuestions(){
        return this.questions;
    }

    public void addConditionsAsKey(List<Variable> conditions){
        if(!this.questions.containsKey(conditions)){
            this.questions.put(conditions, new ArrayList<Question>());
        };
    }
    public void addQuestion(List<Variable> conditions, Question questionStructure){
        this.questions.get(conditions).add(questionStructure); ;
    }

    public List<Variable> getReferencedVariables() {
        return referencedVariables;
    }
    public List<Question> getAllQuestions(){
        List<Question> allQuestions = new ArrayList<Question>();
        for (Map.Entry<List<Variable>, List<Question>> entry : this.getQuestions().entrySet()) {
            for (Question qs : entry.getValue()) {
                allQuestions.add(qs);
            }
        }
        return allQuestions;
    }
    public Variable getVariableByLabel(String label){
        Variable qv = null ;

        for (Map.Entry<List<Variable>, List<Question>> entry : this.getQuestions().entrySet()) {
            for (Question qs : entry.getValue()) {
                if (qs.getVariable().getName().equals(label)){
                    qv = qs.getVariable();
                }
            }
        }
        return qv;
    }
}
