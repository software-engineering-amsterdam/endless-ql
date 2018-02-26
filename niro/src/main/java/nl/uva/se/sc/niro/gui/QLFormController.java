package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.uva.se.sc.niro.model.QLForm;

import java.io.IOException;


public class QLFormController extends QLBaseController {
    @FXML
    private Label formName;

    @FXML
    private GridPane questionsGrid;

    public void populateForm(QLForm form) {
        formName.setText(form.formName().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"));
        questionsGrid.setPadding(new Insets(0, 20, 0, 20));
        WidgetCreatingVisitor.visit(questionsGrid, form.statements(), form.symbolTable());
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Stage stage = getActiveStage(event);
        QLForms.openHomeScreen(stage);
    }

    @FXML
    public void saveData(ActionEvent event) {
        System.out.println("Data is saved....");
    }
}
