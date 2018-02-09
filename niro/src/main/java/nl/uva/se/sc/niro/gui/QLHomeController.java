package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

public class QLHomeController {

    @FXML
    public void openForm(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Form files", "*.ql"));

        File selectedFile = fileChooser.showOpenDialog(getActiveStage(event));
        if (selectedFile != null) {
            System.out.printf("File [%s] has been selected.%n", selectedFile);
        }
    }

    @FXML
    public void quitApplication(ActionEvent event) {
        getActiveStage(event).close();
    }

    private Stage getActiveStage(ActionEvent event) {
        Button closeButton = (Button) event.getSource();
        return (Stage) closeButton.getScene().getWindow();
    }
}
