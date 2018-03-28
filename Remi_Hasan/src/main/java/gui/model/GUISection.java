package gui.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;

import java.util.List;

public class GUISection extends GUISectionElement {

    private final String title;
    private final List<GUISectionElement> sectionElements;

    public GUISection(String identifier, List<GUISectionElement> sectionElements) {
        this.title = identifier;
        this.sectionElements = sectionElements;
    }

    public Parent render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        VBox vBox = new VBox();
        Label pageLabel = new Label("Section " + title);
        vBox.getChildren().add(pageLabel);

        for (GUISectionElement sectionElement : this.sectionElements) {
            vBox.getChildren().add(sectionElement.render(symbolTable, allWidgetsListener));
        }

        vBox.setPadding(new Insets(0, 0, 0, 20));

        return vBox;
    }
}
