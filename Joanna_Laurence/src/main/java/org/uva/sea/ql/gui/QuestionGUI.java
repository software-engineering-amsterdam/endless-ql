package org.uva.sea.ql.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.value.Value;

public class QuestionGUI extends Control {

    private Label label;
    //boolean - checkbox
    //numeric fields - text boxes
    //computed values - label readonly
    private Control type;
    private Value value;
    private boolean isComputed;
    private boolean shouldBeVisible = true;

    public QuestionGUI(Label label, Control type) {
        this.label = label;
        this.type = type;
    }

    public QuestionGUI(QuestionData data) {
        this.label = generateLabel(data.getLabel());
        this.type = generateInput(data.getNodeType());
        this.value = data.getValue();
        this.isComputed = data.isComputed();
    }

    private Label generateLabel(String name) {
        return new Label(name);
    }

    private Control generateInput(NodeType nodeType) {
        switch (nodeType) {
            case BOOLEAN:
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty()
                        .addListener((observable, oldValue, newValue) -> System.out.println("set into " + newValue));
                return checkBox;
            case DECIMAL:
            case INTEGER:
            case MONEY:
            case STRING:
            case DATE:
                TextField textField = new TextField();
                textField.setEditable(true);
                textField.setMinWidth(100.0);
                return textField;
            case UNKNOWN:
            default:
                return new Label("UNKNOWN");
        }
    }

    public boolean isShouldBeVisible() {
        return shouldBeVisible;
    }

    public void setShouldBeVisible(boolean shouldBeVisible) {
        this.shouldBeVisible = shouldBeVisible;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Control getType() {
        return type;
    }

    public void setType(Control type) {
        this.type = type;
    }
}
