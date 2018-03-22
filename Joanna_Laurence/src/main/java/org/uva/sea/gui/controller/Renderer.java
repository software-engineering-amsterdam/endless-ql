package org.uva.sea.gui.controller;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.widget.BaseWidget;

import java.util.*;

public class Renderer {

    private final Map<String, Node> drawNode = new HashMap<>();

    public void clearTabPane(TabPane pane) {
        pane.getTabs().removeAll(pane.getTabs());
    }

    private void clear(Map<String, VBox> guiContainers) {
        for(Pane container : guiContainers.values()) {
            container.getChildren().removeAll(container.getChildren());
        }
    }

    public void draw(Iterable<BaseWidget> displayedComponents, Map<String, VBox> guiContainers) {
        this.drawNode.clear();
        this.clear(guiContainers);
        for(BaseWidget widget : displayedComponents) {
            Node widgetNode = widget.render(guiContainers);
            this.drawNode.put(widget.getIdentifier(), widgetNode);
        }
    }

    public void setFocus(String identifier) {
        Node node = this.drawNode.get(identifier);
        if(node != null)
            node.requestFocus();
    }
}
