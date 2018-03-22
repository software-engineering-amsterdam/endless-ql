package org.uva.sea.gui.components;

import javafx.scene.Group;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class GuiMessage extends Renderable {

    private String message;

    public GuiMessage(String message) {
        this.message = message;
    }

    @Override
    public void render(Group container, TabPane tabPane, VBox messages) {
        messages.getChildren().add(this.createTextRow(this.message));
    }
}
