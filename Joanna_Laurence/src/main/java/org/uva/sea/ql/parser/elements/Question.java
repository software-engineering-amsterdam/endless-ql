package org.uva.sea.ql.parser.elements;

public class Question {
    private String label;
    private String variable;
    private String type;

    public Question(String label, String variable, String type) {

        this.label = label;
        this.variable = variable;
        this.type = type;
    }

    public Question() {
        System.out.println("Question created");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
