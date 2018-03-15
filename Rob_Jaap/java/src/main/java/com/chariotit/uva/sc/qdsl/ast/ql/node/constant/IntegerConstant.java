package com.chariotit.uva.sc.qdsl.ast.ql.node.constant;

import com.chariotit.uva.sc.qdsl.ast.ql.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class IntegerConstant extends Constant {

    private Integer value;

    public IntegerConstant(Integer value, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitIntegerConstant(this);
    }
}
