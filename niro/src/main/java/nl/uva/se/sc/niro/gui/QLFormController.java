package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import nl.uva.se.sc.niro.model.Ast;


public class QLFormController extends QLBaseController {
    @FXML
    private Label formName;

    @FXML
    private GridPane questions;

    public void populateForm(Ast.QLForm form) {
        formName.setText(form.formName().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"));
        StatementFactory.createStatements(questions, form.statements());
    }

    @FXML
    public void saveData(ActionEvent event) {
        System.out.println("Data is saved....");
    }
}
