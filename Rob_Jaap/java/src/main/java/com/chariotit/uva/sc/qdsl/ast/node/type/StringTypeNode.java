package com.chariotit.uva.sc.qdsl.ast.node.type;

import com.chariotit.uva.sc.qdsl.ast.node.TypeNode;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class StringTypeNode extends TypeNode {

    public StringTypeNode(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringType(this);
    }
}
