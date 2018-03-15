package com.chariotit.uva.sc.qdsl.ql.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

import java.util.List;

public class AstRoot extends AstNode {

    private List<Form> forms;
    private SymbolTable symbolTable;

    private AstRoot(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        symbolTable = new SymbolTable();
    }

    public AstRoot(List<Form> forms, Integer lineNumber, Integer columnNumber) {
        this(lineNumber, columnNumber);
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
