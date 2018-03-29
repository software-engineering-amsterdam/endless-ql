package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;

import java.util.Set;

public abstract class Expression extends AstNode {

    public abstract void evaluate(SymbolTable symbolTable);
    public abstract Set<String> getPrerequisites();

    private ExpressionType expressionType;
    private ExpressionValue expressionValue;

    Expression(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType type) {
        this.expressionType = type;
    }

    public ExpressionValue getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(ExpressionValue expressionValue) {
        this.expressionValue = expressionValue;
    }
}
