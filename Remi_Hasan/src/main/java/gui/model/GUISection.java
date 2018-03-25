package gui.model;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import qls.model.QuestionReference;

import java.util.List;
import java.util.Optional;

public class GUISection {

    private final String title;
    private final List<GUIQuestion> questions;

    public GUISection(String identifier, List<GUIQuestion> guiQuestions) {
        this.title = identifier;
        this.questions = guiQuestions;
    }

    public Parent render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        VBox vBox = new VBox();
        Label pageLabel = new Label("Section " + title);
        vBox.getChildren().add(pageLabel);

        for(GUIQuestion question : questions) {
            vBox.getChildren().add(question.render(symbolTable, allWidgetsListener));
        }

        vBox.setPadding(new Insets(0, 0, 0, 20));

        return vBox;
    }
}
