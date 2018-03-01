package org.uva.sea.ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.value.*;

public class QuestionGUI extends Control {

    private String label;
    //boolean - checkbox
    //numeric fields - text boxes
    //computed values - label readonly
    private NodeType type;
    private Value value;
    private String variableName;
    private boolean isComputed;
    private boolean shouldBeVisible = true;

    QuestionGUI(QuestionData data) {
        this.label = data.getLabel();
        this.type = data.getNodeType();
        this.value = data.getValue();
        this.isComputed = data.isComputed();
        this.variableName = data.getQuestionName();
    }

    public String displayValue() {
        switch (type) {
            case BOOLEAN:
                return String.valueOf(((BooleanValue) value).getBooleanValue());
            case DATE:
                return String.valueOf(((DateValue) value).getDateValue());
            case DECIMAL:
                return String.valueOf(((DecimalValue) value).getDecimalValue());
            case INTEGER:
                return String.valueOf(((IntValue) value).getIntValue());
            case MONEY:
                return String.valueOf(((MoneyValue) value).getAmount())
                        + " " + ((MoneyValue) value).getCurrency();
            case STRING:
                return ((StringValue) value).getStringValue();
            case UNKNOWN:
            default:
                return "UNKNOWN";
        }
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public boolean isShouldBeVisible() {
        return shouldBeVisible;
    }

    public void setShouldBeVisible(boolean shouldBeVisible) {
        this.shouldBeVisible = shouldBeVisible;
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
