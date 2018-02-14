package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    //TODO: implement variable name checker before
    public Type getExprType() {
        throw new NotImplementedException();
    }

    public boolean checkType() {
        throw new NotImplementedException();
    }
}
