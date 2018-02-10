package org.uva.sea.ql.parser.elements;

public class Question {
    private String label;
    private String variable;
    private Type type;
    private Expr value;

    public Question(String label, String variable, Type type, Expr value) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Expr getValue() {
        return value;
    }

    public void setValue(Expr value) {
        this.value = value;
    }
}
