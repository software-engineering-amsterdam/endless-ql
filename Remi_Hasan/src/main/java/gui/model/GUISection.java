package gui.model;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import qls.model.DefaultStyle;
import qls.model.Question;
import qls.model.Section;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUISection extends VBox implements GUIInterface{

    private final String identifier;
    private final List<GUIInterface> children;

    public GUISection(String identifier, List<GUIInterface> children){
        this.identifier = identifier;
        this.children = children;

        Label label = new Label("Section " + identifier);
        this.getChildren().add(label);

        for(GUIInterface child : children){
            this.getChildren().add(child.render());
        }

        this.setPadding(new Insets(0, 0, 0, 20));

        this.managedProperty().bind(this.visibleProperty());
    }

//    public Parent render(SymbolTable symbolTable){
//        VBox vBox = new VBox();
//        Label pageLabel = new Label("Section " + identifier);
//        vBox.getChildren().add(pageLabel);
//
//        // Render all sections
//        for(Section section : sections){
//            // Combine local styles with broader scope styles
//            GUISection guiSection = new GUISection(section.identifier, guiQuestions, defaultStyles, section.getDefaultStyles(), section.getQuestions(), section.getSections());
//            vBox.getChildren().add(guiSection.render(symbolTable));
//        }
//
//        for(Question question : questions){
//            Optional<GUIQuestion> guiQuestion = guiQuestions.stream().filter(x -> x.identifier.equals(question.name)).findFirst();
//            GUIQuestionWithStyling questionWithStyling = new GUIQuestionWithStyling(guiQuestion.get(), defaultStyles);
//            vBox.getChildren().add(questionWithStyling.render(symbolTable));
//        }
//
//        vBox.setPadding(new Insets(0, 0, 0, 20));
//
//        return vBox;
//    }

    @Override
    public void update(SymbolTable symbolTable) {
        for(GUIInterface child : children){
            child.update(symbolTable);
        }
    }

    @Override
    public void update(StyleSheet styleSheet) {
        for(GUIInterface child : children){
            child.update(styleSheet);
        }
    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        for(GUIInterface child : children){
            child.setChangeListener(invalidationListener);
        }
    }
}
