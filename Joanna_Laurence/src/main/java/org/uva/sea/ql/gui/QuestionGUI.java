package org.uva.sea.ql.gui;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

public class QuestionGUI extends Control {

    private Label label;

    //boolean - checkbox
    //numeric fields - text boxes
    //computed values - label readonly
    private Control type;

    public QuestionGUI(Label label, Control type) {
        this.label = label;
        this.type = type;
    }
}
