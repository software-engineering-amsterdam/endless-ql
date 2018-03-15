package com.chariotit.uva.sc.qdsl.ql.ast.symboltable;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ql.ast.node.AstNode;

public class SymbolTableEntry {

    private String label;
    private AstNode node;
    private ExpressionType expressionType;


    public SymbolTableEntry(String label, AstNode node) {
        this.label = label;
        this.node = node;
    }

    public SymbolTableEntry(String label, AstNode node, ExpressionType expressionType) {
        this.label = label;
        this.node = node;
        this.expressionType = expressionType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public AstNode getNode() {
        return node;
    }

    public void setNode(AstNode node) {
        this.node = node;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

}
