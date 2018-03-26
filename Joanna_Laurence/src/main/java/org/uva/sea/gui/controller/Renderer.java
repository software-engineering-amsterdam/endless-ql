package org.uva.sea.gui.controller;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.widget.BaseWidget;

import java.util.HashMap;
import java.util.Map;

class Renderer {

    private final Map<String, Node> drawnNodes = new HashMap<>();

    public void clearTabPane(TabPane pane) {
        pane.getTabs().removeAll(pane.getTabs());
    }

    private void clearContainers(Map<String, VBox> guiContainers) {
        for (Pane container : guiContainers.values()) {
            container.getChildren().removeAll(container.getChildren());
        }
    }

    public void draw(Iterable<BaseWidget> displayedComponents, Map<String, VBox> guiContainers) {
        this.drawnNodes.clear();
        this.clearContainers(guiContainers);
        for (BaseWidget widget : displayedComponents) {
            Node widgetNode = widget.render(guiContainers);
            this.drawnNodes.put(widget.getIdentifier(), widgetNode);
        }
    }

    public void setFocus(String identifier) {
        Node node = this.drawnNodes.get(identifier);
        if (node != null)
            node.requestFocus();
    }
}
