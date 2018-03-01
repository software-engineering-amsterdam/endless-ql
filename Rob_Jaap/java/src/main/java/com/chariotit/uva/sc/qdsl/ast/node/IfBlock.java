package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

import java.util.List;

public class IfBlock extends BlockElement {

    private Expression expression;
    private List<FormElement> ifElements;
    private List<FormElement> elseElements;

    public IfBlock(Expression expression, List<FormElement> formElements, Integer lineNumber,
                   Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.expression = expression;
        this.ifElements = formElements;
    }

    public IfBlock(Expression expression, List<FormElement> ifElements, List<FormElement>
            elseElements, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.expression = expression;
        this.ifElements = ifElements;
        this.elseElements = elseElements;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public List<FormElement> getIfElements() {
        return ifElements;
    }

    public void setIfElements(List<FormElement> ifElements) {
        this.ifElements = ifElements;
    }

    public List<FormElement> getElseElements() {
        return elseElements;
    }

    public void setElseElements(List<FormElement> elseElements) {
        this.elseElements = elseElements;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        expression.acceptVisitor(visitor);

        for (FormElement formElement : ifElements) {
            formElement.acceptVisitor(visitor);
        }

        if (elseElements != null) {
            for (FormElement formElement : elseElements) {
                formElement.acceptVisitor(visitor);
            }
        }

        visitor.visitIfBlock(this);
    }
}
