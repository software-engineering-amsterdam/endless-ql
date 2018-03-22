package org.uva.sea.gui.ql;

import javafx.scene.layout.Pane;
import org.uva.sea.gui.ql.widget.Renderable;

import java.util.Map;

public class Renderer {
    private void clear(Map<String, Pane> guiContainers) {
        for(Pane container : guiContainers.values()) {
            container.getChildren().removeAll(container.getChildren());
        }
    }

    public void draw(Iterable<Renderable> displayedComponents, Map<String, Pane> guiContainers) {
        this.clear(guiContainers);
        for(Renderable renderable : displayedComponents) {
            renderable.render(guiContainers);
        }
    }
}
