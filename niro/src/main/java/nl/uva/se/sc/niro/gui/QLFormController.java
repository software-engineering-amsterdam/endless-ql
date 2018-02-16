package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nl.uva.se.sc.niro.model.Ast;


public class QLFormController extends QLBaseController {
    @FXML
    private Label formName;

    @FXML
    private VBox questions;

    @FXML
    private Button save;

    private Ast.QLForm form;

    public void populateForm(Ast.QLForm form) {
        this.form = form;
        formName.setText(form.formName().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"));
        questions.getChildren().addAll(StatementFactory.createStatements(form.statements()));
    }

    @FXML
    public void saveData(ActionEvent event) {
        System.out.println("Data is saved....");
    }
}
