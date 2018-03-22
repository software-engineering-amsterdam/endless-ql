package org.uva.sea.gui.controller.utility;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertBuilder {

    public Alert buildError(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText);
        this.fillAlert("Error", "Error", alert);
        return alert;
    }

    public Alert buildInfo(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, contentText);
        this.fillAlert("Info", "Info", alert);
        return alert;
    }

    public Alert buildWarning(String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING, contentText);
        this.fillAlert("Warning", "Warning", alert);
        return alert;
    }

    private void fillAlert(String header, String title, Alert alert) {
        alert.setHeaderText(header);
        alert.setTitle(title);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }


}
