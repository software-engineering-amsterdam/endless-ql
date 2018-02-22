package domain;

import java.util.*;

public class FormData {
    private List<Question> plainQuestions;
    private Map<String, List<Question>> conditionQuestions;
    public FormData(){
        this.plainQuestions = new ArrayList<Question>();
        this.conditionQuestions = new HashMap<String, List<Question>>();
    }

    public List<Question> getPlainQuestions() {
        return this.plainQuestions;
    }

    public void addPlainQuestion(Question question) {
        this.plainQuestions.add(question);
    }

    public Map<String, List<Question>> getConditionQuestions(){
        return this.conditionQuestions;
    }
    public void addQuestionVariableToConditionQuestions(String questionVariable){
        if(!this.conditionQuestions.containsKey(questionVariable)){
            this.conditionQuestions.put(questionVariable, new ArrayList<Question>());
        };
    }
    public void addConditionQuestion(String questionVariable, Question question){
        this.conditionQuestions.put(questionVariable, Collections.singletonList(question));
    }
}
