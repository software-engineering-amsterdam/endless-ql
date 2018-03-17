package gui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;
import qls.model.DefaultStyle;
import qls.model.Page;
import qls.model.Section;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUIForm extends VBox {

    GUIForm(SymbolTable symbolTable, Form form, StyleSheet qlsStyleSheet) {
        TabPane formPane = new TabPane();

        for(Page page : qlsStyleSheet.getPages()){
            VBox pagePane = new VBox();
            for(Section section : page.getSections()){

                List<DefaultStyle> defaults = section.getDefaultStyles();

                // Add section to page
                VBox sectionGUI = createSectionGUI(symbolTable, form, defaults, section);
                pagePane.getChildren().add(sectionGUI);
                sectionGUI.prefHeightProperty().bind(pagePane.heightProperty());
            }
            VBox.setVgrow(pagePane, Priority.ALWAYS);

            // Add new page as tab to form
            Tab tab = new Tab();
            tab.setText(page.identifier);
            tab.setContent(pagePane);
            formPane.getTabs().add(tab);
            tab.setClosable(false);
        }

        ScrollPane scrollPane = new ScrollPane(formPane);
        formPane.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().add(scrollPane);
        this.setHeight(640);
        this.setWidth(320);
    }

    private VBox createSectionGUI(SymbolTable symbolTable, Form form, List<DefaultStyle> defaultStyles, Section section){
        // Create section
        VBox sectionPane = new VBox();
        for(qls.model.Question qlsQuestion : section.getQuestions()) {
            Node questionGUI = createQuestionGUI(symbolTable, form, defaultStyles, qlsQuestion);
            sectionPane.getChildren().add(questionGUI);
        }
        for(qls.model.Section subSection : section.getSections()){
            List<DefaultStyle> subDefaultStyles = new ArrayList<>();
            subDefaultStyles.addAll(defaultStyles);
            subDefaultStyles.addAll(subSection.getDefaultStyles());
            Node subSectionGUI = createSectionGUI(symbolTable, form, subDefaultStyles, subSection);
            sectionPane.getChildren().add(subSectionGUI);
        }
        sectionPane.setSpacing(10);

        return sectionPane;
    }

    private Node createQuestionGUI(SymbolTable symbolTable, Form form, List<DefaultStyle> defaultStyles, qls.model.Question qlsQuestion){
        Optional<Question> qlQuestion = form.questions.stream().filter(x -> x.name.equals(qlsQuestion.name)).findFirst();
        if (qlQuestion.isPresent()) {
            return new GUIQuestion(symbolTable, defaultStyles, qlQuestion.get());
        } else {
            throw new UnsupportedOperationException("Question with name'" + qlsQuestion.name + "' could not be found");
        }
    }
}
