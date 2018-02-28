package ParseObjects;

import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;

public class Question {
    private String identifier;
    private String text;
    EvaluationType type;
    private Expression answer;

    public Question(String id, String text, EvaluationType type, Expression answer){
        this.identifier = id;
        this.text = text;
        this.type = type;
        this.answer = answer;
    }
}