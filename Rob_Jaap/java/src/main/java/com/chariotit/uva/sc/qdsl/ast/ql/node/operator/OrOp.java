package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.type.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrOp extends Operator implements BinaryOperator {

    public OrOp(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public ExpressionValue evaluate(SymbolTable symbolTable, Expression leftExpression, Expression rightExpression) {
        return ((BooleanExpressionValue) leftExpression.getExpressionValue())
                .or((BooleanExpressionValue) rightExpression.getExpressionValue());
    }

    @Override
    protected List<ExpressionType> getValidExpressionTypes() {
        return new ArrayList<>(
                Arrays.asList(
                        ExpressionType.BOOLEAN
                )
        );
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitOrOp(this);
    }
}
