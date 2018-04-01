package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.type.*;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinusOp extends Operator implements BinaryOperator, UnaryOperator {

    public MinusOp(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMinusOp(this);
    }

    @Override
    public ExpressionValue evaluate(SymbolTable symbolTable, Expression leftExpression, Expression rightExpression) {

        System.out.println("Evaluating minus operation");

        System.out.println( ((MoneyExpressionValue)leftExpression.getExpressionValue()).getValue() );

        System.out.println( ((MoneyExpressionValue)rightExpression.getExpressionValue()).getValue() );

        ExpressionValue value = ((NumberExpressionValue) leftExpression.getExpressionValue())
                .subtract(
                        (NumberExpressionValue)rightExpression.getExpressionValue());

        System.out.println( ((MoneyExpressionValue)value).getValue() );
        return value;

    }

    @Override
    public ExpressionValue evaluate(SymbolTable symbolTable, Expression expression) {

        return ((BooleanExpressionValue)expression.getExpressionValue()).not();
    }

    @Override
    protected List<ExpressionType> getValidExpressionTypes() {
        return new ArrayList<>(
                Arrays.asList(
                        ExpressionType.MONEY,
                        ExpressionType.INTEGER
                )
        );
    }
}
