package com.chariotit.uva.sc.qdsl.ast.qls.node.type;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.qls.node.TypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class BooleanTypeNode extends TypeNode {
    public BooleanTypeNode(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.BOOLEAN;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitBooleanType(this);
    }
}
