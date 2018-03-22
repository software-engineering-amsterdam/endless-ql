package org.uva.sea.gui.ql.components;

import javafx.scene.control.Alert;

public class AlertBuilder {

    public Alert buildError(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText);
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        return alert;
    }

    public Alert buildInfo(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, contentText);
        alert.setHeaderText("Information");
        alert.setTitle("Information");
        return alert;
    }

    public Alert buildWarning(String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING, contentText);
        alert.setHeaderText("Warning");
        alert.setTitle("Warning");
        return alert;
    }
}


