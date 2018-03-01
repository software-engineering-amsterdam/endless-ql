package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

import java.util.List;

public class Form extends AstNode {

    private String label;
    private List<FormElement> formElements;

    public Form(String label, List<FormElement> formElements, Integer lineNumber, Integer
            columnNumber) {
        super(lineNumber, columnNumber);

        this.label = label;
        this.formElements = formElements;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<FormElement> getFormElements() {
        return formElements;
    }

    public void setFormElements(List<FormElement> formElements) {
        this.formElements = formElements;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        for (FormElement formElement : formElements) {
            formElement.acceptVisitor(visitor);
        }

        visitor.visitForm(this);
    }
}
