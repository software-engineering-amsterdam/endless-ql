package nl.uva.se.sc.niro.gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;


public class QLFormController extends QLBaseController {
    @FXML
    private Label formName;

    @FXML
    private VBox questions;

    public void populateForm(String name, List<Parent> controls) {
        formName.setText(name);
        questions.getChildren().addAll(controls);
    }
}
