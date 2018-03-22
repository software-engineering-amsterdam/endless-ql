package gui.model;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import qls.model.DefaultStyle;
import qls.model.Section;

import java.util.ArrayList;
import java.util.List;

public class GUIPage extends VBox{

    private final String identifier;
    private final List<GUIQuestion> guiQuestions;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyles;

    public GUIPage(String identifier, List<GUIQuestion> guiQuestions, List<Section> sections, List<DefaultStyle> defaultStyles) {
        this.identifier = identifier;
        this.guiQuestions = guiQuestions;
        this.sections = sections;
        this.defaultStyles = defaultStyles;
    }

    public VBox render(SymbolTable symbolTable){
        VBox vBox = new VBox();
        Label pageLabel = new Label("Page " + identifier);
        vBox.getChildren().add(pageLabel);

        // Render all sections
        for(Section section : sections){
            // Combine local styles with broader scope styles
            List<DefaultStyle> subDefaultStyles = new ArrayList<>();
            subDefaultStyles.addAll(defaultStyles);
            subDefaultStyles.addAll(section.getDefaultStyles());

            GUISection guiSection = new GUISection(section.identifier, guiQuestions, defaultStyles, section.getDefaultStyles(), section.getQuestions(), section.getSections());
            vBox.getChildren().add(guiSection.render(symbolTable));
        }

        return vBox;
    }
    
}
