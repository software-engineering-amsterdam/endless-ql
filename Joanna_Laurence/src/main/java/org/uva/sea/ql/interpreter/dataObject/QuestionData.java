package org.uva.sea.ql.interpreter.dataObject;

import org.uva.sea.ql.interpreter.evaluate.valueTypes.ErrorValue;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;

public class QuestionData {
    private String label;
    private Value value;
    private boolean isComputed;
    private NodeType nodeType;
    private String questionName;
    private WidgetType widgetType;

    public QuestionData(Question question, Value value) {
        this.label = question.getLabel();
        this.value = doesValueTypeMatch(question, value) ? value : new ErrorValue("Incorrect question type", question.getLine(), question.getColumn());
        this.isComputed = question.getValue() != null;
        this.nodeType = question.getNodeType().getNodeType();
        this.questionName = question.getVariable().getVariableName();
        //TODO: handle setting widget type depending on QLS
        this.widgetType = nodeType.isTypeCompatible(NodeType.BOOLEAN) ? WidgetType.CHECKBOX : WidgetType.TEXTFIELD;
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

    public WidgetType getWidgetType() {
        return widgetType;
    }
}
