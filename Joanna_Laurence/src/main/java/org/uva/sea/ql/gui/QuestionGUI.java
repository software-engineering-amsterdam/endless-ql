package org.uva.sea.ql.gui;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

public class QuestionGUI extends Control {

    private Label label;
    //boolean - checkbox
    //numeric fields - text boxes
    //computed values - label readonly
    private Control type;
    private boolean shouldBeVisible = false;

    public QuestionGUI(Label label, Control type) {
        this.label = label;
        this.type = type;
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
