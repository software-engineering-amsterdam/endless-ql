package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.AstNode;

public abstract class Operator extends AstNode {

    public Operator(SourceFilePosition filePosition) {
        super(filePosition);
    }
}
