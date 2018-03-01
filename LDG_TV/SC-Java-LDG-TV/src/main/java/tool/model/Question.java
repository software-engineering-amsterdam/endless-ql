package tool.model;

import tool.model.variables.Variable;

public class Question {
    private String text;
    private Variable variable;

    public Question(String text, Variable variable) {
        this.text = text;
        this.variable = variable;
    }

    @Override
    public String toString() {
        return this.text + ' ' + this.variable;
    }
}
