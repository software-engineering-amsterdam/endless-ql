package QL.ParseObjectsQL;

import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;

public class Question {
    private String identifier;
    private String text;
    private EvaluationType type;
    private Expression answer;

    public Question(String id, String text, EvaluationType type, Expression answer){
        setIdentifier(id);
        setText(text);
        setType(type);
        setAnswer(answer);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EvaluationType getType() {
        return type;
    }

    public void setType(EvaluationType type) {
        this.type = type;
    }

    public Expression getAnswer() {
        return answer;
    }

    public void setAnswer(Expression answer) {
        this.answer = answer;
    }
}