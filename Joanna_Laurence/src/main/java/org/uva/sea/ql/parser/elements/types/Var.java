package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;

public class Var extends ASTNode {
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
