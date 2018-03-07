package org.uva.sea.gui.model;

import javafx.scene.control.Control;
import org.uva.sea.dataObject.QuestionData;
import org.uva.sea.evaluate.valueTypes.Value;
import org.uva.sea.ql.NodeType;

public abstract class BaseQuestionModel extends Control implements BaseQuestionGUI {

    private String label;
    private NodeType type;
    private Value value;
    private String variableName;
    private boolean isComputed;

    public BaseQuestionModel(QuestionData data) {
        this.label = data.getLabel();
        this.type = data.getNodeType();
        this.value = data.getValue();
        this.isComputed = data.isComputed();
        this.variableName = data.getQuestionName();
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean isComputed() {
        return isComputed;
    }

    public void setComputed(boolean computed) {
        isComputed = computed;
    }
}
