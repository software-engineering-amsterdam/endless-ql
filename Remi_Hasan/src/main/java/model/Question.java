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
    public final Expression condition;

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

    public void typeCheck() {
        if(this.answer.getReturnType() != ReturnType.UNDEFINED &&
                (this.type == ReturnType.INTEGER || this.type == ReturnType.DECIMAL || this.type == ReturnType.MONEY)) {
            if(this.answer.getReturnType() != ReturnType.NUMBER) {
                throw new IllegalArgumentException("Cannot assign '"
                        + this.answer.getReturnType() + "' to '" + this.type + "'");
            }
        } else if(this.answer.getReturnType() != ReturnType.UNDEFINED) {
            if(this.answer.getReturnType() != this.type) {
                throw new IllegalArgumentException("Cannot assign '"
                        + this.answer.getReturnType() + "' to '" + this.type + "'");
            }
        }
    }

    public String evaluateAnswer() {
        ExpressionVariable evaluated = this.answer.evaluate();

        // If undefined, display answer as empty
        if(evaluated.getReturnType() == ReturnType.UNDEFINED) {
            return "";
        }

        switch(this.type) {
            case INTEGER:
                return evaluated.getIntValue().toString();
            case DECIMAL:
                return evaluated.getDecimalValue().toString();
            case MONEY:
                return evaluated.getMoneyValue().toString();
            case STRING:
                return evaluated.getStringValue();
            default:
                return "";
        }
    }
}
