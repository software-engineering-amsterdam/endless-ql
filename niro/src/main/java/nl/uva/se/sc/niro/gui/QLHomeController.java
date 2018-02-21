package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import nl.uva.se.sc.niro.parser.QLFormParser;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;

import static nl.uva.se.sc.niro.model.Ast.QLForm;

public class QLHomeController extends QLBaseController {
    @FXML
    private TextArea errorMessages;

    @FXML
    public void openForm(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select QL form");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("QL Form files", "*.ql"));

        Stage stage = getActiveStage(event);
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                QLForm form = QLFormParser.parse(CharStreams.fromFileName(selectedFile.getAbsolutePath()));
                if (QLFormParser.getParseErrors().isEmpty()) {
                    Scene formScene = createSceneForForm(form);
                    stage.setScene(formScene);
                } else {
                    errorMessages.setText(ErrorUtil.toString(QLFormParser.getParseErrors()));
                    errorMessages.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Scene createSceneForForm(QLForm form) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/uva/se/sc/niro/gui/QLForm.fxml"));
        Parent root = loader.load();
        ((QLFormController) loader.getController()).populateForm(form);
        return new Scene(root);
    }

}
