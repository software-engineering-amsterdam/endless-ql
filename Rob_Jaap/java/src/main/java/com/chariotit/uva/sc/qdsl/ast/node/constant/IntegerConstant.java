package com.chariotit.uva.sc.qdsl.ast.node.constant;

import com.chariotit.uva.sc.qdsl.ast.node.Constant;

public class IntegerConstant extends Constant {

    private Integer value;

    public IntegerConstant(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
