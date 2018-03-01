package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

import java.util.List;

public class AstRoot extends AstNode {

    private List<Form> forms;
    private SymbolTable symbolTable;

    private AstRoot() {
        symbolTable = new SymbolTable();
    }

    public AstRoot(List<Form> forms) {
        this();
        this.forms = forms;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void acceptVisitor(NodeVisitor visitor) {
        for (Form form : forms) {
            form.acceptVisitor(visitor);
        }

        visitor.visitAstRoot(this);
    }
}
