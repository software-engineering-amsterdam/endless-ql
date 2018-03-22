package gui.renderer;

import gui.widgets.GUIWidget;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Form;
import qls.QLSVisitor;
import qls.model.Page;
import qls.model.Question;
import qls.model.Section;
import qls.model.StyleSheet;

public class RenderVisitor extends QLSVisitor<Parent> {

    private final Form form;
    private final SymbolTable symbolTable;

    public RenderVisitor(Form form, SymbolTable symbolTable) {
        this.form = form;
        this.symbolTable = symbolTable;
    }

    @Override
    public Parent visit(StyleSheet styleSheet) {
        TabPane tabPane = new TabPane();

        for (Page page : styleSheet.getPages()) {
            Tab tab = new Tab();
            tab.setClosable(false);
            tab.setText(page.identifier);
            tab.setContent(page.accept(this));

            tabPane.getTabs().add(tab);
        }

        return tabPane;
    }

    @Override
    public Parent visit(Page page) {
        ScrollPane scrollPane = new ScrollPane();

        VBox pageBox = new VBox();
        for (Section section : page.getSections()) {
            pageBox.getChildren().add(section.accept(this));
        }

        scrollPane.setContent(pageBox);
        return scrollPane;
    }

    @Override
    public Parent visit(Section section) {
        VBox sectionBox = new VBox();

        Label label = new Label(section.identifier);
        sectionBox.getChildren().add(label);

        // Render questions
        for (Question question : section.getQuestions()) {
            sectionBox.getChildren().add(question.accept(this));
        }

        // Render subsections
        for (Section subsection : section.getSections()) {
            sectionBox.getChildren().add(subsection.accept(this));
        }

        return sectionBox;
    }

    @Override
    public Parent visit(Question question) {
        VBox questionBox = new VBox();

        // Add all QL questions with this identifier (same identifier can occur multiple times, such as in if/else)
        form.questions.stream().filter(x -> x.identifier.equals(question.name)).forEach(qlQuestion -> {
            Label label = new Label(qlQuestion.label);
//
//            QuestionRenderer questionRenderer = new QuestionRenderer(qlQuestion);
//            GUIWidget questionWidget;
//            if (question.getWidget() == null) {
//                questionWidget = questionRenderer.getDefaultWidget();
//            } else {
//                questionWidget = question.getWidget().accept(questionRenderer);
////            }
//
//            questionBox.getChildren().add(label);
//            questionBox.getChildren().add((Node) questionWidget);
        });

        return questionBox;
    }
}
