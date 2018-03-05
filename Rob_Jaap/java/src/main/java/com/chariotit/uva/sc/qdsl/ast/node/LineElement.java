package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class LineElement extends FormElement {

    private Label label;
    private Question question;
    private TypeExpression typeExpression;

    public LineElement(Label label, Question question, TypeExpression typeExpression, Integer
            lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);

        this.label = label;
        this.question = question;
        this.typeExpression = typeExpression;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public TypeExpression getTypeExpression() {
        return typeExpression;
    }

    public void setTypeExpression(TypeExpression typeExpression) {
        this.typeExpression = typeExpression;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        label.acceptVisitor(visitor);
        question.acceptVisitor(visitor);
        typeExpression.acceptVisitor(visitor);

        visitor.visitLineElement(this);
    }
}
