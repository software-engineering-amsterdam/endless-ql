package org.uva.sea.gui;

import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.components.Renderable;

public class Renderer {
    private VBox container;
    private TabPane tabPane;
    private VBox messages;

    public Renderer(VBox container, TabPane tabPane, VBox messages) {
        this.container = container;
        this.tabPane = tabPane;
        this.messages = messages;
    }

    private void clear() {
        this.container.getChildren().removeAll(this.container.getChildren());
        //this.tabPane.getTabs().removeAll();
        this.messages.getChildren().removeAll(this.messages.getChildren());
    }

    public void draw(Iterable<Renderable> displayedComponents) {
        this.clear();
        for(Renderable renderable : displayedComponents) {
            renderable.render(this.container, this.tabPane, this.messages);
        }
    }
}
