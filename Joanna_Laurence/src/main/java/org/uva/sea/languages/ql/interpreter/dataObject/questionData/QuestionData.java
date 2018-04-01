package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.ErrorValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.qls.interpreter.widget.Style;
import org.uva.sea.languages.qls.interpreter.widget.WidgetType;

public class QuestionData {
    private final String label;
    private final Value value;
    private final boolean isComputed;
    private final NodeType nodeType;
    private final String questionName;

    private Style style = new Style();

    public QuestionData(Question question, Value value) {
        this.label = question.getLabel();
        this.value = this.doesValueTypeMatch(question, value) ? value : new ErrorValue("Incorrect questionData type", question.getLine(), question.getColumn());
        this.isComputed = question.getValue() != null;
        this.nodeType = question.getNodeType().getNodeType();
        this.questionName = question.getVariable().getVariableName();
    }

    private boolean doesValueTypeMatch(Question question, Value value) {
        return (value == null) || question.getType().getNodeType().isTypeCompatible(value.getType());

    }

    public String getLabel() {
        return this.label;
    }

    public Value getValue() {
        return this.value;
    }

    public boolean isComputed() {
        return this.isComputed;
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public String getQuestionName() {
        return this.questionName;
    }

    public void mergeStyle(Style style) {
        if (style != null)
            this.style.fillNullFields(style);
    }

    public Style getStyle() {
        return this.style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public WidgetType getWidgetType() {
        return this.style.getWidgetType();
    }

    public void setWidgetType(WidgetType widgetType) {
        this.style.setWidgetType(widgetType);
    }
}
