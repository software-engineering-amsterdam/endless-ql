package org.uva.sea.gui.ql;

import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.ql.widget.Renderable;

import java.util.Map;

public class Renderer {

    public void clearTabPane(TabPane pane) {
        pane.getTabs().removeAll(pane.getTabs());
    }

    private void clear(Map<String, VBox> guiContainers) {
        for(Pane container : guiContainers.values()) {
            container.getChildren().removeAll(container.getChildren());
        }
    }

    public void draw(Iterable<Renderable> displayedComponents, Map<String, VBox> guiContainers) {
        this.clear(guiContainers);
        for(Renderable renderable : displayedComponents) {
            renderable.render(guiContainers);
        }
    }
}
