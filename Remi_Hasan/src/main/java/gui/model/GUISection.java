package gui.model;

import gui.render.GUIController;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class GUISection extends GUIElement {

    private final String title;
    private final List<GUIElement> sectionElements;

    public GUISection(String identifier, List<GUIElement> sectionElements) {
        this.title = identifier;
        this.sectionElements = sectionElements;
    }

    public Parent render(GUIController guiController) {
        Label pageLabel = new Label(title);
        pageLabel.setFont(new Font(16));

        VBox vBox = new VBox();
        vBox.getChildren().add(pageLabel);

        // Render all section elements, keep track of their visibility properties to bind to the section visibility
        ObservableList<BooleanProperty> visibleProperties = FXCollections.observableArrayList();
        for (GUIElement sectionElement : this.sectionElements) {
            Parent renderedElement = sectionElement.render(guiController);
            vBox.getChildren().add(renderedElement);
            visibleProperties.add(renderedElement.visibleProperty());
        }

        if (visibleProperties.size() > 0) {
            // Bind section visibility to question visibility
            vBox.visibleProperty().bind(this.bindingOrConjunction(visibleProperties));
        } else {
            // Empty section, so hide it
            vBox.setVisible(false);
        }

        return vBox;
    }

    // Source: https://stackoverflow.com/a/14707660
    private BooleanBinding bindingOrConjunction(ObservableList<BooleanProperty> properties) {
        BooleanBinding conjunction = new SimpleBooleanProperty(false).or(properties.get(0));
        for (int i = 1; i < properties.size(); i++) {
            conjunction = conjunction.or(properties.get(i));
        }
        return conjunction;
    }
}
