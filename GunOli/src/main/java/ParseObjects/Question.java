package ParseObjects;

import ParseObjects.Expressions.Expression;

public class Question {
    private String identifier;
    private String text;
    private Expression answer;

    public Question(String id, String text, Expression answer){
        this.identifier = id;
        this.text = text;
        this.answer = answer;
    }
}