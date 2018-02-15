package nl.uva.se.sc.niro.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import nl.uva.se.sc.niro.model.Ast;
import scala.collection.Iterator;


public class QLFormController extends QLBaseController {
    @FXML
    public Label formName;

    @FXML
    public GridPane questions;

    public void populateQuestions(Ast.QLForm form) {
        formName.setText(form.formName());

        Iterator<Ast.Statement> stmtIter = form.statements().iterator();
        while (stmtIter.hasNext()) {
            Ast.Statement stmt = stmtIter.next();
            System.out.println(stmt);
        }
    }
}
