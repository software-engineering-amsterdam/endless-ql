package gui.model;

import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import qls.model.DefaultStyle;
import qls.model.Section;

import java.util.ArrayList;
import java.util.List;

public class GUIPage {

    private final String identifier;
    private final List<GUISectionElement> elements;

    public GUIPage(String identifier, List<GUISectionElement> elements) {
        this.identifier = identifier;
        this.elements = elements;
    }

    public String getIdentifier() {
        return identifier;
    }

    public VBox render(SymbolTable symbolTable, InvalidationListener allWidgetsListener){
        VBox vBox = new VBox();

        // Render all sections
        for(GUISectionElement element : elements){
            vBox.getChildren().add(element.render(symbolTable, allWidgetsListener));
        }

        return vBox;
    }
    
}
