package com.chariotit.uva.sc.qdsl.ast.node.type;

import com.chariotit.uva.sc.qdsl.ast.node.Type;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class StringType extends Type {

    public StringType(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringType(this);
    }
}
