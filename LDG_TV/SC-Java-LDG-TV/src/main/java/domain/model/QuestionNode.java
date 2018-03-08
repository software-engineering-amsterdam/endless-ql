package domain.model;

import domain.model.variable.Variable;

public class QuestionNode extends Node {
    private String text;
    private Variable variable;

    public QuestionNode(String text, Variable variable) {
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
        return this.text;
    }
}
