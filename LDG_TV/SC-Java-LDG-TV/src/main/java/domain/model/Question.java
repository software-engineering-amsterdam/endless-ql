package domain.model;

import domain.model.variable.Variable;

public class Question {
    private String text;
    private Variable variable;

    public Question(String text, Variable variable) {
        this.text = text;
        this.variable = variable;
    }

    public String getText() {
        return text;
    }

    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return this.text + ' ' + this.variable;
    }
}
