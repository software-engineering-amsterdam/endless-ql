package com.chariotit.uva.sc.qdsl.ast.ql.node.type;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.TypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class IntegerTypeNode extends TypeNode {

    public IntegerTypeNode(SourceFilePosition filePosition) {
        super(filePosition);
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
