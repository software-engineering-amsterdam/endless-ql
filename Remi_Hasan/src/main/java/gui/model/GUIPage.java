package gui.model;

import gui.render.GUIController;
import javafx.geometry.Insets;
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

    public VBox render(GUIController guiController){
        VBox vBox = new VBox();

        // Render all sections
        for(GUIElement element : elements){
            vBox.getChildren().add(element.render(guiController));
        }

        vBox.setPadding(new Insets(10));
        return vBox;
    }
    
}
