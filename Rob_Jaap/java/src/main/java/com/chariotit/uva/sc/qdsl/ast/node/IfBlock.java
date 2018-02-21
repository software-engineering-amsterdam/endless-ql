package com.chariotit.uva.sc.qdsl.ast.node;

import java.util.List;

public class IfBlock extends BlockElement {

    private Expression expression;
    private List<FormElement> formElements;

    public IfBlock(Expression expression, List<FormElement> formElements) {
        this.expression = expression;
        this.formElements = formElements;
    }

    public Expression getStatement() {
        return expression;
    }

    public void setStatement(Expression expression) {
        this.expression = expression;
    }

    public List<FormElement> getFormElements() {
        return formElements;
    }

    public void setFormElements(List<FormElement> formElements) {
        this.formElements = formElements;
    }
}
