package org.uva.sea.gui.components;

import javafx.scene.control.Alert;

public class AlertBuilder {

    private Alert alert;

    public AlertBuilder setType(Alert.AlertType alertType) {
        alert = new Alert(alertType);
        return this;
    }

    public AlertBuilder setTitle(String title) {
        alert.setTitle(title);
        return this;
    }

    public AlertBuilder setHeaderText(String headerText) {
        alert.setHeaderText(headerText);
        return this;
    }

    public AlertBuilder setContentText(String contentText) {
        alert.setContentText(contentText);
        return this;
    }

    public Alert build() {
        return alert;
    }
}


