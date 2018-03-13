package ParseObjectsQL;

import java.util.HashMap;
import java.util.Map;

public class QuestionMap {
    private Map<String, Question> questionMap;

    public QuestionMap(Form form){
        this.questionMap = new HashMap<>();
        populateMap(form);
    }

    private void populateMap(Form form){
        for(Question question : form.getBlock().getQuestions()){
            this.questionMap.put(question.getIdentifier(), question);
        }
    }

    public Question getQuestion(String id){
        return this.questionMap.get(id);
    }
}
