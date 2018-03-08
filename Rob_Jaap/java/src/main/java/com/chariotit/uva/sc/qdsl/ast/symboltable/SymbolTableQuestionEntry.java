package com.chariotit.uva.sc.qdsl.ast.symboltable;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.node.AstNode;

public class SymbolTableQuestionEntry extends SymbolTableEntry {

    private ExpressionType expressionType;

    public SymbolTableQuestionEntry(String label, AstNode node) {
        super(label, node);
    }

    public SymbolTableQuestionEntry(String label, AstNode node, ExpressionType expressionType) {
        super(label, node);
        this.expressionType = expressionType;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

}
