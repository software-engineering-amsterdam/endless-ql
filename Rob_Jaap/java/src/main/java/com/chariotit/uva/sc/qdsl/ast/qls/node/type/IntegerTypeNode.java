package com.chariotit.uva.sc.qdsl.ast.qls.node.type;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.qls.node.TypeNode;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class IntegerTypeNode extends TypeNode {

    public IntegerTypeNode(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.INTEGER;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitIntegerType(this);
    }
}
