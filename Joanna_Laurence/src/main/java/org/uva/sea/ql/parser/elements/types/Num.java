package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;

public class Num extends ASTNode {
    private int value;

    public Num(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
