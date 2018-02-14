package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.Expr;

public class Bool extends Expr {
    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
