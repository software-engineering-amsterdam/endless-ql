package model;

import analysis.SymbolTable;
import expression.Expression;

public class Question {

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression defaultAnswer;
    private final Expression condition;
    private final boolean isEditable;

    public Question(ReturnType type, String name, String text, Expression defaultAnswer, boolean isEditable, Expression condition) {
        this.type = type;
        this.name = name;
        this.text = text;
        this.defaultAnswer = defaultAnswer;
        this.isEditable = isEditable;
        this.condition = condition;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        return this.condition.evaluate(symbolTable).getBooleanValue();
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void typeCheck(SymbolTable symbolTable) {
        this.condition.typeCheck(symbolTable);
        this.defaultAnswer.typeCheck(symbolTable);

        // Compare defaultAnswer value type to question type
        if(this.type == ReturnType.INTEGER || this.type == ReturnType.DECIMAL || this.type == ReturnType.MONEY) {
            if(this.defaultAnswer.getReturnType(symbolTable) != ReturnType.NUMBER) {
                throw new IllegalArgumentException("Cannot assign '"
                        + this.defaultAnswer.getReturnType(symbolTable) + "' to '" + this.type + "'");
            }
        } else if(this.defaultAnswer.getReturnType(symbolTable) != this.type) {
            throw new IllegalArgumentException("Cannot assign '"
                    + this.defaultAnswer.getReturnType(symbolTable) + "' to '" + this.type + "'");
        }
    }
}
