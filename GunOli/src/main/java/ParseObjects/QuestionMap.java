package ParseObjects;

import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.Constant;

import java.util.HashMap;
import java.util.Map;

public class QuestionMap {
    private Map<String, Expression> questionMap;

    public QuestionMap(Form form){
        this.questionMap = new HashMap<>();
        populateMapFromForm(form);
    }

    private void populateMapFromForm(Form form) {
        for (Question question : form.getBlock().getQuestions()) {
            this.questionMap.put(question.getIdentifier(), question.getAnswer());
        }

        for (Condition condition : form.getBlock().getConditions()) {
            for (Question question : condition.getBlock().getQuestions()) {
                this.questionMap.put(question.getIdentifier(), question.getAnswer());
            }
        }
    }

    public Expression getQuestion(String id){
        return questionMap.get(id);
    }
}
