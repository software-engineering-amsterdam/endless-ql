package com.chariotit.uva.sc.qdsl.ast.node.constant;

import com.chariotit.uva.sc.qdsl.ast.node.Constant;

public class BooleanConstant extends Constant {

    private Boolean value;

    public BooleanConstant(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
