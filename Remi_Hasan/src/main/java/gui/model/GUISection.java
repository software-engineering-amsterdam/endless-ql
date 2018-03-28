package gui.model;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;

import java.util.List;

public class GUISection extends GUIElement {

    private final String title;
    private final List<GUIElement> sectionElements;

    public GUISection(String identifier, List<GUIElement> sectionElements) {
        this.title = identifier;
        this.sectionElements = sectionElements;
    }

    public Parent render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        VBox vBox = new VBox();
        Label pageLabel = new Label("Section " + title);
        vBox.getChildren().add(pageLabel);

        for (GUIElement sectionElement : this.sectionElements) {
            vBox.getChildren().add(sectionElement.render(symbolTable, allWidgetsListener));
        }

        vBox.setPadding(new Insets(0, 0, 0, 20));

        return vBox;
    }
}
