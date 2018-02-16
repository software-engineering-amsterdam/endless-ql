package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import nl.uva.se.sc.niro.parser.QLFormParser$;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static nl.uva.se.sc.niro.model.Ast.QLForm;

public class QLHomeController extends QLBaseController {

    @FXML
    public void openForm(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Form files", "*.ql"));

        Stage stage = getActiveStage(event);
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            System.out.printf("File [%s] has been selected.%n", selectedFile);
            try {
                QLForm form = QLFormParser$.MODULE$.parse(CharStreams.fromFileName(selectedFile.getAbsolutePath()));
                System.out.printf("Form [%s]%n", form);
                Scene formScene = createSceneForForm(form);
                stage.setScene(formScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Scene createSceneForForm(QLForm form) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/uva/se/sc/niro/gui/QLForm.fxml"));
        List<Parent> questions = StatementFactory.createStatements(form.statements());

        Parent root = loader.load();
        ((QLFormController)loader.getController()).populateForm(form.formName(), questions);
        return new Scene(root);
    }

}
