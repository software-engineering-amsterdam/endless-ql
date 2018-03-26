package org.uva.sea.gui.controller.utility;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public final class AlertPopup {

    public static void displayError(final String contentText) {
        final Alert alert = new Alert(Alert.AlertType.ERROR, contentText);
        AlertPopup.fillAlert("Error", "Error", alert);
        alert.show();
    }

    public static void displayInfo(final String contentText) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION, contentText);
        AlertPopup.fillAlert("Info", "Info", alert);
        alert.show();
    }

    public static void displayWarning(final String contentText) {
        final Alert alert = new Alert(Alert.AlertType.WARNING, contentText);
        AlertPopup.fillAlert("Warning", "Warning", alert);
        alert.show();
    }

    private static void fillAlert(final String header, final String title, final Alert alert) {
        alert.setHeaderText(header);
        alert.setTitle(title);

        final Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }
}
