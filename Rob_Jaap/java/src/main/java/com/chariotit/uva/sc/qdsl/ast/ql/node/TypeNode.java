package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;

public abstract class TypeNode extends AstNode {



    public TypeNode(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public abstract ExpressionType getType();
}
