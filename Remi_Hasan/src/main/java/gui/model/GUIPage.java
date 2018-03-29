package gui.model;

import gui.render.GUIController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GUIPage {

    private final String identifier;
    private final List<GUIElement> elements;

    public GUIPage(String identifier, List<GUIElement> elements) {
        this.identifier = identifier;
        this.elements = elements;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Parent render(GUIController guiController) {
        VBox pageBox = new VBox();

        // Render all sections
        for (GUIElement element : elements) {
            pageBox.getChildren().add(element.render(guiController));
        }

        pageBox.setPadding(new Insets(10));

        // Wrap page in scroll pane, so questions will always be reachable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pageBox);
        return scrollPane;
    }

}
