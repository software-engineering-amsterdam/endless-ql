package org.uva.sea.gui.newImpl.components;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class GuiMessage extends Renderable {

    private String message;

    public GuiMessage(String message) {
        this.message = message;
    }

    @Override
    public void render(VBox container, TabPane tabPane, VBox messages) {
        messages.getChildren().add(this.createMessageRow(this.message));
    }
}
