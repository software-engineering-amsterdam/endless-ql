package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;

public abstract class Expression extends AstNode {


    private ExpressionType type;

    Expression(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public ExpressionType getExpressionType() {
        return type;
    }

    public void setExpressionType(ExpressionType type) {
        this.type = type;
    }
}
