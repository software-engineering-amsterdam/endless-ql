package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.HashSet;
import java.util.Set;

public class LineElement extends FormElement {

    private Label label;
    private Question question;
    private TypeExpression typeExpression;
    private Boolean visible;

    public LineElement(Label label, Question question, TypeExpression typeExpression,
                       SourceFilePosition filePosition) {
        super(filePosition);

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

    public LineElement setVisible(Boolean visible) {

        this.visible = visible;

        return this;
    }

    public Boolean isVisible() {
        return this.visible;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public TypeExpression getTypeExpression() {
        return typeExpression;
    }

    @Override
    public Set<String> getPrerequisites() {
        return typeExpression.getExpression().getPrerequisites();
    }

    @Override
    public Set<String> getProducedLabels() {
        Set<String> set = new HashSet<>();
        set.add(label.getLabel());
        return set;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        label.acceptVisitor(visitor);
        question.acceptVisitor(visitor);
        typeExpression.acceptVisitor(visitor);

        visitor.visitLineElement(this);
    }
}
