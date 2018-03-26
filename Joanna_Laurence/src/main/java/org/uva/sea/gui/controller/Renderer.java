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

    public final void clearTabPane(final TabPane pane) {
        pane.getTabs().removeAll(pane.getTabs());
    }

    private void clearContainers(final Map<String, VBox> guiContainers) {
        for (final Pane container : guiContainers.values()) {
            container.getChildren().removeAll(container.getChildren());
        }
    }

    public final void draw(final Iterable<BaseWidget> displayedComponents, final Map<String, VBox> guiContainers) {
        this.drawnNodes.clear();
        this.clearContainers(guiContainers);
        for (final BaseWidget widget : displayedComponents) {
            final Node widgetNode = widget.render(guiContainers);
            this.drawnNodes.put(widget.getIdentifier(), widgetNode);
        }
    }

    public final void setFocus(final String identifier) {
        final Node node = this.drawnNodes.get(identifier);
        if (node != null)
            node.requestFocus();
    }
}
