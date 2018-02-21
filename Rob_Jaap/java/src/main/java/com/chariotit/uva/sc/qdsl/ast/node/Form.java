package com.chariotit.uva.sc.qdsl.ast.node;

import java.util.List;

public class Form extends AstNode {

    private String label;
    private List<FormElement> formElements;

    public Form(String label, List<FormElement> formElements) {
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
}
