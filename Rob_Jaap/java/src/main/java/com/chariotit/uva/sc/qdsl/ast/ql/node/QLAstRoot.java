package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.List;

public class QLAstRoot extends AstNode {

    private List<Form> forms;

    private SymbolTable symbolTable;

    private QLAstRoot(SourceFilePosition filePosition) {
        super(filePosition);
        symbolTable = new SymbolTable();
    }

    public QLAstRoot(List<Form> forms, SourceFilePosition filePosition) {
        this(filePosition);
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
