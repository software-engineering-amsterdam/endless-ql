package tool;

import domain.Utilities;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ToolController implements Initializable {

    @FXML
    private TextArea taSourceCode;

    @FXML
    private ListView<HBox> lvQuestionnaire;

    public ToolController() {
        System.out.println("Class initialized");
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Pane initialized");
    }

    /**
     * Invoked by the 'build' button action, to generate the questionnaire based on the written QL
     * @param event that kicked of the invocation
     */
    public void generateQuestionnaire(ActionEvent event) {
        // TODO generate questionnaire from textarea
        System.out.println("Build");

        HBox l = new HBox();
        //l.setAlignment(Pos.BASELINE_RIGHT);
        Label llbl = new Label("Left");
        l.getChildren().add(llbl);

        HBox r = new HBox();
        //r.setAlignment(Pos.BASELINE_LEFT);
        Label rlbl = new Label("Right");
        r.getChildren().add(rlbl);

        HBox q = new HBox();
        q.setAlignment(Pos.CENTER);
        q.getChildren().addAll(l, r);

        ObservableList<HBox> items = FXCollections.observableArrayList(l);
        lvQuestionnaire.setItems(items);

    }

    /**
     * Invoked by the 'Import' button action, import .QL file
     * @param event that kicked of the invocation
     */
    public void importQLFile(ActionEvent event) {
        FileChooser fileChooser = getFileChooser();

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile == null) {
            return;
        }

        Optional<String> qlText = Utilities.readFile(selectedFile.getAbsolutePath());

        qlText.ifPresentOrElse(
                text -> taSourceCode.setText(text),
                () -> showAlertBox("Could not read file.")
        );
    }

    private void showAlertBox(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
        alert.showAndWait();
    }

    private FileChooser getFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QL File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Questionnaire Language File (*.ql)", "*.ql"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        return fileChooser;
    }
}
