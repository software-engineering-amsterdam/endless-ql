package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.ErrorValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Question;

public class QuestionData {
    private final String label;
    private final Value value;
    private final boolean isComputed;
    private final NodeType nodeType;
    private final String questionName;

    private Style style = new Style();

    public QuestionData(final Question question, final Value value) {
        this.label = question.getLabel();
        this.value = this.doesValueTypeMatch(question, value) ? value : new ErrorValue("Incorrect questionData type", question.getLine(), question.getColumn());
        this.isComputed = question.getValue() != null;
        this.nodeType = question.getNodeType().getNodeType();
        this.questionName = question.getVariable().getVariableName();
    }

    private boolean doesValueTypeMatch(final Question question, final Value value) {
        return (value == null) || question.getType().getNodeType().isTypeCompatible(value.getType());

    }

    public final String getLabel() {
        return this.label;
    }

    public final Value getValue() {
        return this.value;
    }

    public final boolean isComputed() {
        return this.isComputed;
    }

    public final NodeType getNodeType() {
        return this.nodeType;
    }

    public final String getQuestionName() {
        return this.questionName;
    }

    public final void mergeStyle(final Style style) {
        if (style != null)
            this.style.fillNullFields(style);
    }

    public final Style getStyle() {
        return this.style;
    }

    public final void setStyle(final Style style) {
        this.style = style;
    }

    public final WidgetType getWidgetType() {
        return this.style.getWidgetType();
    }

    public final void setWidgetType(final WidgetType widgetType) {
        this.style.setWidgetType(widgetType);
    }
}
