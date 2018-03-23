package com.chariotit.uva.sc.qdsl.ast.ql.node.type;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.TypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class MoneyTypeNode extends TypeNode {

    public MoneyTypeNode(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.MONEY;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMoneyType(this);
    }
}
