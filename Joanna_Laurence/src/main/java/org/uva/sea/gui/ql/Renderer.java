package org.uva.sea.gui.ql;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.ql.widget.Renderable;
import org.uva.sea.gui.ql.widget.Widget;

import java.util.*;

public class Renderer {

    private Map<String, Node> drawNode = new HashMap<>();

    public void clearTabPane(TabPane pane) {
        pane.getTabs().removeAll(pane.getTabs());
    }

    private void clear(Map<String, VBox> guiContainers) {
        for(Pane container : guiContainers.values()) {
            container.getChildren().removeAll(container.getChildren());
        }
    }

    public void draw(Iterable<Widget> displayedComponents, Map<String, VBox> guiContainers) {
        this.drawNode.clear();
        this.clear(guiContainers);
        for(Widget widget : displayedComponents) {
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
