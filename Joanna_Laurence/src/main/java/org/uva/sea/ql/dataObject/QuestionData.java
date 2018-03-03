package org.uva.sea.ql.dataObject;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.evaluate.valueTypes.ErrorValue;
import org.uva.sea.ql.evaluate.valueTypes.Value;

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

    public String getQuestionName() {
        return questionName;
    }
}
