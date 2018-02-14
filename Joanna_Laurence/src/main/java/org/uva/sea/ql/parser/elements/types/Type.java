package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;

public class Type extends ASTNode {
    private String type;

    public Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Type getExprType() {
        return new Type(type);
    }

    public boolean checkType() {
        return true;
    }

    public boolean equals(Type name) {
        return this.type.equals(name);
    }
}
