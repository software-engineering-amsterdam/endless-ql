package org.uva.sea.ql.DataObject;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.value.Value;

public class QuestionData {
    private String label;
    private Value value;
    private boolean isComputed;
    private NodeType nodeType;

    public QuestionData(Question question, Value value) {
        this.label = question.getLabel();
        this.value = value;
        this.isComputed = question.getValue() != null;
        this.nodeType = question.getNodeType().getNodeType();
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
