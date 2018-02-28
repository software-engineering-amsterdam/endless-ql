package org.uva.sea.ql.DataObject;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.value.ErrorValue;
import org.uva.sea.ql.value.Value;

public class QuestionData {
    private String label;
    private Value value;
    private boolean isComputed;
    private NodeType nodeType;

    public QuestionData(Question question, Value value) {
        this.label = question.getLabel();
        this.value = doesValueTypeMatch(question, value) ? value : new ErrorValue("Incorrect question type", question.getLine(), question.getColumn());
        this.isComputed = question.getValue() != null;
        this.nodeType = question.getNodeType().getNodeType();
    }

    private boolean doesValueTypeMatch(Question question, Value value) {
        if(value == null)
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
}
