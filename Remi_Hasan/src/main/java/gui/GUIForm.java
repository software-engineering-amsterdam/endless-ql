package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;
import qls.model.Page;
import qls.model.Section;
import qls.model.StyleSheet;

import java.util.Optional;
import java.util.UnknownFormatConversionException;

public class GUIForm extends VBox {

    GUIForm(SymbolTable symbolTable, Form form, StyleSheet qlsStyleSheet) {
        TabPane formPane = new TabPane();

        for(Page page : qlsStyleSheet.getPages()){
            VBox pagePane = new VBox();
            for(Section section : page.getSections()){
                // Add section to page
                Node sectionGUI = createSectionGUI(symbolTable, form, section);
                pagePane.getChildren().add(sectionGUI);
            }

            // Add new page as tab to form
            Tab tab = new Tab();
            tab.setText(page.identifier);
            tab.setContent(pagePane);
            formPane.getTabs().add(tab);
            tab.setClosable(false);
        }

        ScrollPane scrollPane = new ScrollPane(formPane);
        this.setPrefHeight(500);
        this.getChildren().add(scrollPane);
    }

    private Node createSectionGUI(SymbolTable symbolTable, Form form, Section section){
        // Create section
        VBox sectionPane = new VBox();
        for(qls.model.Question qlsQuestion : section.getQuestions()) {
            Node questionGUI = createQuestionGUI(symbolTable, form, qlsQuestion);
            sectionPane.getChildren().add(questionGUI);
        }
        for(qls.model.Section subSection : section.getSections()){
            Node subSectionGUI = createSectionGUI(symbolTable, form, subSection);
            sectionPane.getChildren().add(subSectionGUI);
        }
        sectionPane.setPadding(new Insets(20, 20, 20, 20));
        sectionPane.setSpacing(10);

        return sectionPane;
    }

    private Node createQuestionGUI(SymbolTable symbolTable, Form form, qls.model.Question qlsQuestion){
        Optional<Question> qlQuestion = form.questions.stream().filter(x -> x.name.equals(qlsQuestion.name)).findFirst();
        if (qlQuestion.isPresent()) {
            return new GUIQuestion(symbolTable, qlQuestion.get());
        } else {
            throw new UnsupportedOperationException("Question with name'" + qlsQuestion.name + "' could not be found");
        }
    }
}
