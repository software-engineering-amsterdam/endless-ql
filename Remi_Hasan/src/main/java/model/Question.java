package model;

import expression.Expression;
import expression.ReturnType;
import expression.variable.ExpressionVariable;
import expression.variable.ExpressionVariableBoolean;
import expression.variable.ExpressionVariableUndefined;

public class Question {

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression answer;
    private final Expression condition;

    public Question(ReturnType type, String name, String text, Expression answer, Expression condition) {
        this.type = type;
        this.name = name;
        this.text = text;
        this.answer = answer;
        this.condition = condition;
    }

    public boolean isVisible() {
        return this.condition.evaluate().getBooleanValue();
    }

    public Object evaluateAnswer() {
        ExpressionVariable evaluated = this.answer.evaluate();

        // If undefined, display answer as empty
        if(evaluated.getReturnType() == ReturnType.UNDEFINED) {
            return "";
        }

        switch(this.type) {
            case INTEGER:
                return evaluated.getIntValue();
            case DECIMAL:
                return evaluated.getDecimalValue();
            case MONEY:
                return evaluated.getMoneyValue();
            case STRING:
                return evaluated.getStringValue();
            default:
                return new ExpressionVariableUndefined();
        }
    }


}
