package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.List;

public class QLAstRoot extends AstNode {

    private List<Form> forms;
    private SymbolTable formSymbolTable;
    private SymbolTable questionSymbolTable;

    private QLAstRoot(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        formSymbolTable = new SymbolTable();
        questionSymbolTable = new SymbolTable();
    }

    public QLAstRoot(List<Form> forms, Integer lineNumber, Integer columnNumber) {
        this(lineNumber, columnNumber);
        this.forms = forms;

    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public SymbolTable getFormSymbolTable() {
        return formSymbolTable;
    }

    public void setFormSymbolTable(SymbolTable formSymbolTable) {
        this.formSymbolTable = formSymbolTable;
    }

    public SymbolTable getQuestionSymbolTable() {
        return questionSymbolTable;
    }

    public void setQuestionSymbolTable(SymbolTable questionSymbolTable) {
        this.questionSymbolTable = questionSymbolTable;
    }

    public void acceptVisitor(NodeVisitor visitor) {
        for (Form form : forms) {
            form.acceptVisitor(visitor);
        }

        visitor.visitAstRoot(this);
    }
}
