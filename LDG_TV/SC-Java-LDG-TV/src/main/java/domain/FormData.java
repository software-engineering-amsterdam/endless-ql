package domain;

import domain.model.Condition;
import domain.model.question.QuestionStructure;
import domain.model.question.QuestionVariable;

import java.util.*;

public class FormData {
    private List<QuestionStructure> plainQuestionStructures;
    private Map<List<Condition>, List<QuestionStructure>> conditionQuestions;
    private List<QuestionVariable> referencedVariables;

    public FormData(){
        this.plainQuestionStructures = new ArrayList<QuestionStructure>();
        this.conditionQuestions = new HashMap<List<Condition>, List<QuestionStructure>>();
        this.referencedVariables = new ArrayList<QuestionVariable>();
    }

    public List<QuestionStructure> getPlainQuestionStructures() {
        return this.plainQuestionStructures;
    }

    public void addPlainQuestion(QuestionStructure questionStructure) {
        this.plainQuestionStructures.add(questionStructure);
    }

    public Map<List<Condition>, List<QuestionStructure>> getConditionQuestions(){
        return this.conditionQuestions;
    }
    public void addConditionsAsKey(List<Condition> conditions){
        if(!this.conditionQuestions.containsKey(conditions)){
            this.conditionQuestions.put(conditions, new ArrayList<QuestionStructure>());
        };
    }
    public void addConditionQuestion(List<Condition> conditions, QuestionStructure questionStructure){
        this.conditionQuestions.get(conditions).add(questionStructure); ;
    }

    public List<QuestionVariable> getReferencedVariables() {
        return referencedVariables;
    }
    public List<QuestionStructure> getAllQuestions(){
        List<QuestionStructure> allQuestions = new ArrayList<QuestionStructure>();
        for (Map.Entry<List<Condition>, List<QuestionStructure>> entry : this.getConditionQuestions().entrySet()) {
            for (QuestionStructure qs : entry.getValue()) {
                allQuestions.add(qs);
            }
        }
        allQuestions.addAll(this.plainQuestionStructures);
        return allQuestions;
    }
    public QuestionVariable getQuestionVariableByLabel(String label){
        QuestionVariable qv = null ;

        for (QuestionStructure qs : this.getPlainQuestionStructures()){
            if (qs.getQuestionVariable().getLabel().equals(label)){
                qv = qs.getQuestionVariable();
            }
        }
        if (qv == null) {
            for (Map.Entry<List<Condition>, List<QuestionStructure>> entry : this.getConditionQuestions().entrySet()) {
                for (QuestionStructure qs : entry.getValue()) {
                    if (qs.getQuestionVariable().getLabel().equals(label)){
                        qv = qs.getQuestionVariable();
                    }
                }
            }
        }
        return qv;
    }
}
