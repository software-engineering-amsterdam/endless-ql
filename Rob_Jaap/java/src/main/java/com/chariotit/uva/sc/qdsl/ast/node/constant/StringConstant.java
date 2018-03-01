package com.chariotit.uva.sc.qdsl.ast.node.constant;

import com.chariotit.uva.sc.qdsl.ast.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class StringConstant extends Constant {

    private String value;

    public StringConstant(String value, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringConstant(this);
    }
}
