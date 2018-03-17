package gui;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;

public class GUIForm extends VBox {

    GUIForm(SymbolTable symbolTable, Form form) {
        VBox questionPane = new VBox();
        for (Question question : form.questions) {
            questionPane.getChildren().add(new GUIQuestion(symbolTable, question));
        }
        questionPane.setPadding(new Insets(20, 20, 20, 20));
        questionPane.setSpacing(10);


        ScrollPane scrollPane = new ScrollPane(questionPane);
        this.setPrefHeight(500);
        this.getChildren().add(scrollPane);
    }
}
