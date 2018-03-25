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
    private final List<GUISection> sections;

    public GUIPage(String identifier, List<GUISection> sections) {
        this.identifier = identifier;
        this.sections = sections;
    }

    public String getIdentifier() {
        return identifier;
    }

    public VBox render(SymbolTable symbolTable, InvalidationListener allWidgetsListener){
        VBox vBox = new VBox();

        // Render all sections
        for(GUISection section : sections){
            vBox.getChildren().add(section.render(symbolTable, allWidgetsListener));
        }

        return vBox;
    }
    
}
