package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.AstNode;

import java.util.List;

public abstract class Operator extends AstNode {

    protected abstract List<ExpressionType> getValidExpressionTypes();

    public Operator(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public boolean isValidExpressionType(ExpressionType expressionType) {
        return getValidExpressionTypes().contains(expressionType);
    }

    public ExpressionType getResultExpressionType(ExpressionType operandExpressionType) {
        return operandExpressionType;
    }

}
