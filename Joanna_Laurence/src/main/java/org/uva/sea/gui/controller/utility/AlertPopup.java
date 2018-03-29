package org.uva.sea.gui.controller.utility;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertPopup {

    public static void displayError(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText);
        AlertPopup.fillAlert("Error", "Error", alert);
        alert.show();
    }

    public static void displayInfo(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, contentText);
        AlertPopup.fillAlert("Info", "Info", alert);
        alert.show();
    }

    public static void displayWarning(String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING, contentText);
        AlertPopup.fillAlert("Warning", "Warning", alert);
        alert.show();
    }

    private static void fillAlert(String header, String title, Alert alert) {
        alert.setHeaderText(header);
        alert.setTitle(title);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }
}
