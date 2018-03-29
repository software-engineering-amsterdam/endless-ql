package gui;

import gui.builder.GUIFormBuilder;
import gui.model.GUIForm;
import gui.render.GUIController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import ql.QLEvaluator;
import qls.QLSFormBuilder;
import qls.model.StyleSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public class Renderer extends Application {
    private QLEvaluator qlEvaluator;
    private StyleSheet qlsStyleSheet;

    @Override
    public void start(Stage primaryStage) {
        ClassLoader classLoader = getClass().getClassLoader();
        File resourceFolder = new File(classLoader.getResource("java/").getFile());
        File qlFile = new File(resourceFolder.getAbsolutePath() + "/example.ql");
        File qlsFile = new File(resourceFolder.getAbsolutePath() + "/example.qls");
        File formExportFile = new File(resourceFolder.getAbsolutePath() + "/export.json");
        try {
            formExportFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.qlEvaluator = new QLEvaluator(new FileInputStream(qlFile));

            // Check for warning messages
            Set<String> warnings = qlEvaluator.getWarnings();
            if (!warnings.isEmpty()) {
                showWarningAlert(String.join("\n", warnings));
            }

            QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(qlEvaluator.getForm());
            this.qlsStyleSheet = qlsFormBuilder.parseStyleSheet(new FileInputStream(qlsFile));
        } catch (FileNotFoundException e) {
            showErrorAlert(e, "Form file not found");
            return;
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            showErrorAlert(e, "Form invalid");
            return;
        } catch (IOException e) {
            showErrorAlert(e, "IO exception while lexing form file");
            return;
        } catch (ParseCancellationException e) {
            showErrorAlert(e, "Error while parsing form file");
            return;
        }

        buildQuestions(primaryStage);

        // TODO move to a better place
//        try {
//            System.out.println("exporting1");
//            SymbolTableExporter.export(symbolTable, formExportFile);
//        } catch (FileNotFoundException e) {
//            showErrorAlert(e, "Could not write to export file, check file permissions.");
//        }

        primaryStage.show();
    }

    public void buildQuestions(Stage primaryStage) {
        // Set locale to US such that DecimalFormat, such as in a spinner, always uses dots instead of commas
        Locale.setDefault(Locale.US);

        GUIFormBuilder guiFormBuilder = new GUIFormBuilder();

        GUIForm guiForm;
        if (this.qlsStyleSheet != null) {
            guiForm = guiFormBuilder.buildQLSForm(qlEvaluator.getForm(), this.qlsStyleSheet);
        } else {
            guiForm = guiFormBuilder.buildQLForm(qlEvaluator.getForm());
        }

        GUIController guiController = new GUIController(qlEvaluator);

        VBox vBox = new VBox();

        // File open/save menu
        Menu fileMenu = new Menu("File");
        MenuItem loadQlFile = new MenuItem("Load QL file");
        MenuItem loadQlsFile = new MenuItem("Load QLS file");
        MenuItem exportResults = new MenuItem("Export results");

        exportResults.setOnAction(event -> {
            this.saveClicked(primaryStage);
        });

        fileMenu.getItems().addAll(loadQlFile, loadQlsFile, exportResults);

        vBox.getChildren().add(new MenuBar(fileMenu));
        vBox.getChildren().add(guiForm.render(guiController));

        Scene scene = new Scene(vBox);
        primaryStage.setTitle(qlEvaluator.getIdentifier());
        primaryStage.setScene(scene);
        primaryStage.setWidth(640);
        primaryStage.setHeight(480);
        primaryStage.show();
    }

    private void saveClicked(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt"));
        File exportFile = fileChooser.showSaveDialog(primaryStage);

        if(exportFile == null) {
            return;
        }

        try {
            qlEvaluator.exportResults(exportFile);
        } catch (IOException e) {
            this.showErrorAlert(e, "Unable to export results to selected file location");
        }
    }

    private void showErrorAlert(Exception e, String message) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    private void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
