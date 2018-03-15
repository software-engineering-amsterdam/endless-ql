package com.chariotit.uva.sc.qdsl.ql.ast.node.type;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ql.ast.node.TypeNode;
import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class StringTypeNode extends TypeNode {

    public StringTypeNode(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.STRING;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringType(this);
    }
}
