package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;

public interface BinaryOperator {

    ExpressionValue evaluate(SymbolTable symbolTable, Expression leftExpression, Expression
            rightExpression);
}
