package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

import java.util.List;

public class IfBlock extends BlockElement {

    private Expression expression;
    private List<FormElement> formElements;

    public IfBlock(Expression expression, List<FormElement> formElements) {
        this.expression = expression;
        this.formElements = formElements;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public List<FormElement> getFormElements() {
        return formElements;
    }

    public void setFormElements(List<FormElement> formElements) {
        this.formElements = formElements;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        expression.acceptVisitor(visitor);

        for (FormElement formElement : formElements) {
            formElement.acceptVisitor(visitor);
        }

        visitor.visitIfBlock(this);
    }
}
