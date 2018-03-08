import analysis.CycleDetector;
import analysis.ReferencedIdentifiersVisitor;
import analysis.SymbolTable;
import analysis.TypeChecker;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Form;
import model.stylesheet.StyleSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("QL form file selector");

        Button fileSelectorButton = createFileSelectorButton(stage);

        // Put button inside a box with spacing
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(fileSelectorButton);

        // Create entire scene
        Scene scene = new Scene(vBox, 300, 100);
        stage.setScene(scene);
        stage.show();
    }

    private Button createFileSelectorButton(Stage stage) {
        // Resource path TODO: remove after debugging
        File resourcesFile = new File(getClass().getResource("java/example.ql").getFile());
        File resourceDirectory = new File(resourcesFile.getParentFile().getAbsolutePath());

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(resourceDirectory);
        final Button openButton = new Button("Browse files...");

        openButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                loadForm(stage, file);
            }
        });

        return openButton;
    }

    private void loadForm(Stage stage, File file) {
        try {
            Form form = FormParser.parseForm(new FileInputStream(file));

            SymbolTable symbolTable = new SymbolTable(form);

            ReferencedIdentifiersVisitor referencedIdentifiersVisitor = new ReferencedIdentifiersVisitor(form);
            List<String> unknownReferencedIdentifiers = referencedIdentifiersVisitor.getUnknownReferencedIdentifiers();
            if(!unknownReferencedIdentifiers.isEmpty()){
                showErrorAlert("Unknown variables detected for the following variables:", unknownReferencedIdentifiers);
                return;
            }

            CycleDetector cycleDetector = new CycleDetector(form);
            Set<String> cycles = cycleDetector.detectCycles();

            if (!cycles.isEmpty()) {
                showErrorAlert("Cycles detected in the following variables:", cycles);
                return;
            }

            TypeChecker typeChecker = new TypeChecker(form, symbolTable);
            Set<String> typeCheckErrors = typeChecker.typeCheck();

            if (!typeCheckErrors.isEmpty()) {
                showErrorAlert("Type checking errors:", typeCheckErrors);
                return;
            }

            File styleSheetFile = new File(file.getParentFile().getAbsolutePath() + "/example.qls");
            StyleSheet styleSheet = StyleSheetParser.parseStyleSheet(new FileInputStream(styleSheetFile));

            Renderer renderer = new Renderer(form, symbolTable, styleSheet);
            renderer.renderForm(stage);
        } catch (FileNotFoundException e) {
            showErrorAlert(e, "Form file not found");
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            // TODO Explain why form is invalid
            showErrorAlert(e, "Form invalid");
        } catch (IOException e) {
            showErrorAlert(e, "IO exception while lexing form file");
        }
    }

    private void showErrorAlert(String description, Collection<String> messages) {
        Alert alert = new Alert(Alert.AlertType.ERROR, description);
        alert.setContentText(description + "\n" + String.join("\n", messages));
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    private void showErrorAlert(Exception e, String message) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setContentText(e.toString());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}