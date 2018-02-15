package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import nl.uva.se.sc.niro.parser.QLFormParser$;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;

import static nl.uva.se.sc.niro.model.Ast.QLForm;

public class QLHomeController {

    @FXML
    public void openForm(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Form files", "*.ql"));

        File selectedFile = fileChooser.showOpenDialog(getActiveStage(event));
        if (selectedFile != null) {
            System.out.printf("File [%s] has been selected.%n", selectedFile);
            try {
                QLForm form = QLFormParser$.MODULE$.parse(CharStreams.fromFileName(selectedFile.getAbsolutePath()));
                System.out.printf("Form [%s]%n", form);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
