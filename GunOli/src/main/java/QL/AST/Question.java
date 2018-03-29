package QL.AST;

import QL.Analysis.EvaluationType;
import QL.AST.Expressions.Expression;

public class Question extends ASTNode {
    private String identifier;
    private String text;
    private EvaluationType type;
    private Expression answer;
    private Expression condition;
    private Boolean predefined;

    public Question(String identifier, String text, EvaluationType type, Expression answer,
                    Expression condition, Boolean predefined, int lineNumber){
        super(lineNumber);
        this.identifier = identifier;
        this.text = text;
        this.type = type;
        this.answer = answer;
        this.condition = condition;
        this.predefined = predefined;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getText() {
        return text;
    }

    public EvaluationType getType() {
        return type;
    }

    public Expression getAnswer() {
        return answer;
    }

    public Expression getCondition(){ return condition;}

    public Boolean isPredefined(){ return predefined;}

    public Boolean isEnabled(){
        return condition.evaluate().getValue().equals(true);
    }
}