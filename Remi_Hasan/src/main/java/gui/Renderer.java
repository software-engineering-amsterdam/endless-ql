package gui;

import gui.builder.GUIFormBuilder;
import gui.model.GUIForm;
import gui.render.GUIController;
import javafx.application.Application;
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

    private File resourceFolder;
    private File qlFile;
    private File qlsFile;

    private VBox formArea = new VBox();

    public Renderer() {
        super();

        // Set locale to US such that DecimalFormat, such as in a spinner, always uses dots instead of commas
        Locale.setDefault(Locale.US);

        ClassLoader classLoader = getClass().getClassLoader();
        resourceFolder = new File(classLoader.getResource("java/").getFile());
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();

        // File open/save menu
        MenuBar menuBar = createMenuBar(primaryStage);

        vBox.getChildren().add(menuBar);
        vBox.getChildren().add(formArea);

        // Make the form area grow with the size of the window
        VBox.setVgrow(formArea, Priority.ALWAYS);

        Scene scene = new Scene(vBox);
        primaryStage.setTitle("Form");
        primaryStage.setScene(scene);
        primaryStage.setWidth(640);
        primaryStage.setHeight(480);
        primaryStage.show();
    }

    private void render(Stage primaryStage) {
        try {
            if(this.qlFile != null){
                this.qlEvaluator = new QLEvaluator(new FileInputStream(this.qlFile));

                // Check for warning messages
                Set<String> warnings = qlEvaluator.getWarnings();
                if (!warnings.isEmpty()) {
                    showWarningAlert(String.join("\n", warnings));
                }

                if(this.qlsFile != null){
                    QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(qlEvaluator.getForm());
                    this.qlsStyleSheet = qlsFormBuilder.parseStyleSheet(new FileInputStream(qlsFile));
                }

                buildQuestions(primaryStage);
                primaryStage.show();
            }
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
    }

    public void buildQuestions(Stage primaryStage) {
        GUIFormBuilder guiFormBuilder = new GUIFormBuilder();

        GUIForm guiForm;
        if (this.qlsStyleSheet != null) {
            guiForm = guiFormBuilder.buildQLSForm(qlEvaluator.getForm(), this.qlsStyleSheet);
        } else {
            guiForm = guiFormBuilder.buildQLForm(qlEvaluator.getForm());
        }

        GUIController guiController = new GUIController(qlEvaluator);

        formArea.getChildren().clear();
        formArea.getChildren().add(guiForm.render(guiController));
    }

    private MenuBar createMenuBar(Stage primaryStage){
        Menu fileMenu = new Menu("File");
        MenuItem loadQlFile = new MenuItem("Load QL file");
        MenuItem loadQlsFile = new MenuItem("Load QLS file");
        MenuItem exportResults = new MenuItem("Export results");

        loadQlFile.setOnAction(e -> {
            loadQLClicked(primaryStage);
            render(primaryStage);
        });

        loadQlsFile.setOnAction(e -> {
            loadQLSClicked(primaryStage);
            render(primaryStage);
        });

        exportResults.setOnAction(event -> {
            this.saveClicked(primaryStage);
        });

        fileMenu.getItems().addAll(loadQlFile, loadQlsFile, exportResults);
        return new MenuBar(fileMenu);
    }

    private void loadQLClicked(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(resourceFolder);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QL File (*.ql)", "*.ql"));
        this.qlFile = fileChooser.showOpenDialog(primaryStage);
    }

    private void loadQLSClicked(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(resourceFolder);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QLS File (*.qls)", "*.qls"));
        this.qlsFile = fileChooser.showOpenDialog(primaryStage);
    }

    private void saveClicked(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        String homeDirectory = System.getProperty("user.home") + "/Desktop";
        fileChooser.setInitialDirectory(new File(homeDirectory));
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
