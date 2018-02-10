package org.uva.sea.ql.parser.elements;

public class Var extends Expr {
    private String variableName;

    public Var(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
}
