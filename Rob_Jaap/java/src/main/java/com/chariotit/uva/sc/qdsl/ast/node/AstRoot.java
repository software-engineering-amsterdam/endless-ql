package com.chariotit.uva.sc.qdsl.ast.node;

import java.util.List;

public class AstRoot extends AstNode {

    private List<Form> forms;

    public AstRoot(List<Form> forms) {
        this.forms = forms;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
}
