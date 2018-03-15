package com.chariotit.uva.sc.qdsl.ql.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;

public abstract class TypeNode extends AstNode {



    public TypeNode(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public abstract ExpressionType getType();
}
