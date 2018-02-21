package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QLBaseController {
    @FXML
    public void quitApplication(ActionEvent event) {
        getActiveStage(event).close();
    }

    protected Stage getActiveStage(ActionEvent event) {
        Button closeButton = (Button) event.getSource();
        return (Stage) closeButton.getScene().getWindow();
    }
}
