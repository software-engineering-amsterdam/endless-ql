package com.chariotit.uva.sc.qdsl.ast.symboltable;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.node.AstNode;

public abstract class SymbolTableEntry {

    private String label;
    private AstNode node;
    private ExpressionType expressionType;


    public SymbolTableEntry(String label, AstNode node) {
        this.label = label;
        this.node = node;
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

}
