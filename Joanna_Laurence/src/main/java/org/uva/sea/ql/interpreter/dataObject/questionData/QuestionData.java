package org.uva.sea.ql.interpreter.dataObject.questionData;

import org.uva.sea.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.ErrorValue;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.qls.parser.elements.style.StyleSpecification;
import org.uva.sea.qls.parser.elements.style.Widget;

import java.util.List;

public class QuestionData {
    private String label;
    private Value value;
    private boolean isComputed;
    private NodeType nodeType;
    private String questionName;

    private Style style = new Style();
    private WidgetType widgetType;

    public QuestionData(Question question, Value value) {
        this.label = question.getLabel();
        this.value = doesValueTypeMatch(question, value) ? value : new ErrorValue("Incorrect questionData type", question.getLine(), question.getColumn());
        this.isComputed = question.getValue() != null;
        this.nodeType = question.getNodeType().getNodeType();
        this.questionName = question.getVariable().getVariableName();
    }

    private boolean doesValueTypeMatch(Question question, Value value) {
        if (value == null)
            return true;

        return question.getType().getNodeType().isTypeCompatible(value.getType());
    }

    public String getLabel() {
        return label;
    }

    public Value getValue() {
        return value;
    }

    public boolean isComputed() {
        return isComputed;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void mergeStyle(Style style) {
        if(style != null)
            this.style.fillNullFields(style);
    }

    public Style getStyle() {
        return style;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }
}
