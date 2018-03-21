package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableUndefined;
import qls.model.DefaultStyle;
import qls.model.Page;
import qls.model.Section;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUIForm extends VBox {

    GUIForm(SymbolTable symbolTable, Form form, StyleSheet qlsStyleSheet) {
        TabPane tabPane = new TabPane();

        int pageId = 1;
        for (Page page : qlsStyleSheet.getPages()) {
            VBox pagePane = createPageGUI(symbolTable, form, String.valueOf(pageId), page);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(pagePane);

            // Add new page as tab to form
            Tab tab = new Tab();
            tab.setText(page.identifier);
            tab.setContent(scrollPane);
            tabPane.getTabs().add(tab);
            tab.setClosable(false);

            pageId++;
        }

        this.getChildren().add(tabPane);
        this.setPrefHeight(640);
        this.setPrefWidth(480);

        // Scale tab pane to window size
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        // TODO refactor
        // Re-evaluation
        symbolTable.setExpression("dummy", new ExpressionVariableUndefined(null, null));
    }

    private VBox createPageGUI(SymbolTable symbolTable, Form form, String prefix, Page page){
        VBox pagePane = new VBox();
        int sectionId = 1;
        for (Section section : page.getSections()) {
            List<DefaultStyle> defaults = section.getDefaultStyles();

            // Add section to page
            VBox sectionGUI = createSectionGUI(symbolTable, form, defaults, prefix + "." + sectionId, section);
            Label sectionLabel = new Label("Section " + prefix + "." + sectionId + ": " + section.identifier);
            sectionLabel.setFont(Font.font(sectionLabel.getFont().getFamily(), FontWeight.BOLD, sectionLabel.getFont().getSize()));
            pagePane.getChildren().add(sectionLabel);
            pagePane.getChildren().add(sectionGUI);

            sectionId++;
        }
        return pagePane;
    }

    private VBox createSectionGUI(SymbolTable symbolTable, Form form, List<DefaultStyle> defaultStyles, String prefix, Section section) {
        // Create section
        VBox sectionPane = new VBox();
        for (qls.model.Question qlsQuestion : section.getQuestions()) {
            Node questionGUI = createQuestionGUI(symbolTable, form, defaultStyles, qlsQuestion);
            sectionPane.getChildren().add(questionGUI);
        }
        int sectionId = 1;
        for (qls.model.Section subSection : section.getSections()) {
            Label sectionLabel = new Label("Section " + prefix + "." + sectionId + ": " + subSection.identifier);
            sectionLabel.setFont(Font.font(sectionLabel.getFont().getFamily(), FontWeight.BOLD, sectionLabel.getFont().getSize()));
            sectionPane.getChildren().add(sectionLabel);
            List<DefaultStyle> subDefaultStyles = new ArrayList<>();
            subDefaultStyles.addAll(defaultStyles);
            subDefaultStyles.addAll(subSection.getDefaultStyles());
            Node subSectionGUI = createSectionGUI(symbolTable, form, subDefaultStyles, prefix + "." + sectionId, subSection);
            sectionPane.getChildren().add(subSectionGUI);

            sectionId++;
        }
        sectionPane.setSpacing(10);

        return sectionPane;
    }

    private Node createQuestionGUI(SymbolTable symbolTable, Form form, List<DefaultStyle> defaultStyles, qls.model.Question qlsQuestion) {
        Optional<Question> qlQuestion = form.questions.stream().filter(x -> x.identifier.equals(qlsQuestion.name)).findFirst();
        if (qlQuestion.isPresent()) {
            return new GUIQuestion(symbolTable, qlQuestion.get(), qlsQuestion, defaultStyles);
        } else {
            throw new UnsupportedOperationException("Question with identifier'" + qlsQuestion.name + "' could not be found");
        }
    }
}
