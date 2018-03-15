package QL.ParseObjectsQL;

import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;

public class Question {
    private String identifier;
    private String text;
    private EvaluationType type;
    private Expression answer;
    private Expression condition;
    private Boolean predefined;

    public Question(String id, String text, EvaluationType type, Expression answer, Expression condition, Boolean predefined){
        setIdentifier(id);
        setText(text);
        setType(type);
        setAnswer(answer);
        setCondition(condition);
        setPredefined(predefined);
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

    public Expression getCondition(){ return condition;}

    public void setCondition(Expression condition){ this.condition = condition;}

    private void setPredefined(Boolean predefined){
        this.predefined = predefined;
    }

    public Boolean isPredefined(){ return predefined;}

    public Boolean isEnabled(){
        return condition.evaluate().getValue().equals(true);
    }
}