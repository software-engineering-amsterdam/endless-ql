package domain;

import domain.model.Condition;
import domain.model.question.QuestionStructure;
import domain.model.question.QuestionVariable;

import java.util.*;

public class FormData {
    private Map<List<Condition>, List<QuestionStructure>> questions;
    private List<QuestionVariable> referencedVariables;

    public FormData(){
        this.questions = new HashMap<List<Condition>, List<QuestionStructure>>();
        this.referencedVariables = new ArrayList<QuestionVariable>();
    }

    public Map<List<Condition>, List<QuestionStructure>> getQuestions(){
        return this.questions;
    }
    public void addConditionsAsKey(List<Condition> conditions){
        if(!this.questions.containsKey(conditions)){
            this.questions.put(conditions, new ArrayList<QuestionStructure>());
        };
    }
    public void addQuestion(List<Condition> conditions, QuestionStructure questionStructure){
        this.questions.get(conditions).add(questionStructure); ;
    }

    public List<QuestionVariable> getReferencedVariables() {
        return referencedVariables;
    }
    public List<QuestionStructure> getAllQuestions(){
        List<QuestionStructure> allQuestions = new ArrayList<QuestionStructure>();
        for (Map.Entry<List<Condition>, List<QuestionStructure>> entry : this.getQuestions().entrySet()) {
            for (QuestionStructure qs : entry.getValue()) {
                allQuestions.add(qs);
            }
        }
        return allQuestions;
    }
    public QuestionVariable getQuestionVariableByLabel(String label){
        QuestionVariable qv = null ;

        for (Map.Entry<List<Condition>, List<QuestionStructure>> entry : this.getQuestions().entrySet()) {
            for (QuestionStructure qs : entry.getValue()) {
                if (qs.getQuestionVariable().getLabel().equals(label)){
                    qv = qs.getQuestionVariable();
                }
            }
        }
        return qv;
    }
}
