package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.type.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotOp extends Operator implements UnaryOperator {

    public NotOp(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNotOp(this);
    }

    @Override
    public ExpressionValue evaluate(SymbolTable symbolTable, Expression expression) {
        System.out.println("In NotOp eval");
        System.out.println(expression);
        expression.evaluate(symbolTable);

        System.out.println(expression.getExpressionValue());
        return ((BooleanExpressionValue)expression.getExpressionValue()).not();
    }

    @Override
    protected List<ExpressionType> getValidExpressionTypes() {
        return new ArrayList<>(
                Arrays.asList(
                        ExpressionType.BOOLEAN
                )
        );
    }
}
