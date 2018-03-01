package domain;

import domain.model.question.QuestionStructure;

import java.util.*;

public class FormData {
    private List<QuestionStructure> plainQuestionStructures;
    private Map<String, List<QuestionStructure>> conditionQuestions;
    private List<String> referencedVariables;
    public FormData(){
        this.plainQuestionStructures = new ArrayList<QuestionStructure>();
        this.conditionQuestions = new HashMap<String, List<QuestionStructure>>();
        this.referencedVariables = new ArrayList<String>();
    }

    public List<QuestionStructure> getPlainQuestionStructures() {
        return this.plainQuestionStructures;
    }

    public void addPlainQuestion(QuestionStructure questionStructure) {
        this.plainQuestionStructures.add(questionStructure);
    }

    public Map<String, List<QuestionStructure>> getConditionQuestions(){
        return this.conditionQuestions;
    }
    public void addQuestionVariableToConditionQuestions(String questionVariable){
        if(!this.conditionQuestions.containsKey(questionVariable)){
            this.conditionQuestions.put(questionVariable, new ArrayList<QuestionStructure>());
        };
    }
    public void addConditionQuestion(String questionVariable, QuestionStructure questionStructure){
        this.conditionQuestions.put(questionVariable, Collections.singletonList(questionStructure));
    }

    public List<String> getReferencedVariables() {
        return referencedVariables;
    }
}
