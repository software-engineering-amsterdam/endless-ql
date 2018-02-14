package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.Expr;

public class Str extends Expr {
    private String value;

    public Str(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
