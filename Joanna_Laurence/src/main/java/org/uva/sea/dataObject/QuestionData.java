package org.uva.sea.dataObject;

import org.uva.sea.evaluate.valueTypes.ErrorValue;
import org.uva.sea.evaluate.valueTypes.Value;
import org.uva.sea.ql.NodeType;
import org.uva.sea.ql.elements.Question;

public class QuestionData {
    private String label;
    private Value value;
    private boolean isComputed;
    private NodeType nodeType;
    private String questionName;

    public QuestionData(Question question, Value value) {
        this.label = question.getLabel();
        this.value = doesValueTypeMatch(question, value) ? value : new ErrorValue("Incorrect question type", question.getLine(), question.getColumn());
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
}
