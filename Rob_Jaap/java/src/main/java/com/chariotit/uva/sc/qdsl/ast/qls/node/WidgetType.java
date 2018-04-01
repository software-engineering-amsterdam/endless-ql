package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;

public abstract class WidgetType extends AstNode {

    public WidgetType(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public abstract boolean isValidExpressionType(ExpressionType expressionType);
}
