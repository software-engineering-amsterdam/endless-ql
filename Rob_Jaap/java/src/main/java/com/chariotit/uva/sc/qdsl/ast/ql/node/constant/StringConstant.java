package com.chariotit.uva.sc.qdsl.ast.ql.node.constant;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.type.StringExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Constant;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class StringConstant extends Constant {

    private StringExpressionValue value;

    public StringConstant(StringExpressionValue value, SourceFilePosition filePosition) {
        super(filePosition);
        this.value = value;
    }

    public StringExpressionValue getValue() {
        return value;
    }

    public void setValue(StringExpressionValue value) {
        this.value = value;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitStringConstant(this);
    }
}
