package com.chariotit.uva.sc.qdsl.ast.node.constant;

import com.chariotit.uva.sc.qdsl.ast.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class MoneyConstant extends Constant {

    private Float value;

    public MoneyConstant(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMoneyConstant(this);
    }
}
