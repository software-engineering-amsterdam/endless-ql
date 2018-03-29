package gui.model;

import gui.GUIController;
import gui.elements.LabelWithWidget;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.*;

public class GUIForm extends VBox {
    public final String identifier;
    private final List<GUIQuestion> guiQuestions;

    public GUIForm(String identifier, List<GUIQuestion> guiQuestions) {
        this.identifier = identifier;
        this.guiQuestions = guiQuestions;
    }

    public Parent render(GUIController guiController) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        // Render all QL questions in order
        for (GUIQuestion guiQuestion : this.guiQuestions) {
            LabelWithWidget labelWithWidget = guiQuestion.render(guiController);
            vBox.getChildren().add(labelWithWidget);
        }

        guiController.updateQuestionWidgets();

        // Wrap form in scroll pane, so questions will always be reachable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        return scrollPane;
    }
}