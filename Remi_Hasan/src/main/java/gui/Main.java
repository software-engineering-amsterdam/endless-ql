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
import ql.QLForm;
import qls.QLSBuilder;
import qls.model.StyleSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public class Main extends Application {
    private QLForm qlForm;
    private StyleSheet qlsStyleSheet;

    private File qlFile;
    private File qlsFile;

    private VBox formArea = new VBox();

    public Main() {
        super();

        // Set locale to US such that DecimalFormat, such as in a spinner, always uses dots instead of commas
        Locale.setDefault(Locale.US);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox mainWindowBox = new VBox();

        // File open/save menu
        MenuBar menuBar = createMenuBar(primaryStage);

        mainWindowBox.getChildren().add(menuBar);
        mainWindowBox.getChildren().add(this.formArea);

        // Make the form area grow with the size of the window
        VBox.setVgrow(this.formArea, Priority.ALWAYS);

        Scene scene = new Scene(mainWindowBox);
        primaryStage.setTitle("Form Renderer");
        primaryStage.setScene(scene);
        primaryStage.setWidth(640);
        primaryStage.setHeight(480);
        primaryStage.show();
    }

    private void fileLoaded(Stage primaryStage) {
        // New form, so clear currently rendered one
        this.formArea.getChildren().clear();

        this.buildForm();

        if(this.qlForm == null) {
            return;
        }

        // Check for warning messages
        Set<String> warnings = qlForm.getWarnings();
        if (!warnings.isEmpty()) {
            showWarningAlert(String.join("\n", warnings));
        }

        this.buildStyleSheet();

        this.renderForm(primaryStage);
    }

    private void buildForm() {
        if (this.qlFile == null) {
            return;
        }

        try {
            this.qlForm = new QLForm(new FileInputStream(this.qlFile));
        } catch (FileNotFoundException e) {
            handleQLError(e, "Form file not found");
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            handleQLError(e, "Form invalid");
        } catch (IOException e) {
            handleQLError(e, "IO exception while lexing QL file");
        } catch (ParseCancellationException e) {
            handleQLError(e, "Error while parsing QL file");
        }
    }

    private void buildStyleSheet() {
        if (this.qlForm == null || this.qlsFile == null) {
            return;
        }

        try {
            QLSBuilder qlsBuilder = new QLSBuilder(this.qlForm.getForm());
            this.qlsStyleSheet = qlsBuilder.parseStyleSheet(new FileInputStream(this.qlsFile));
        } catch (FileNotFoundException e) {
            handleQLSError(e, "Form file not found");
        } catch (IllegalArgumentException e) {
            handleQLSError(e, "StyleSheet invalid");
        } catch (IOException e) {
            handleQLSError(e, "IO exception while lexing QLS file");
        } catch (ParseCancellationException e) {
            handleQLSError(e, "Error while parsing QLS file");
        }
    }

    private void renderForm(Stage primaryStage) {
        GUIFormBuilder guiFormBuilder = new GUIFormBuilder();

        GUIForm guiForm;
        if (this.qlsStyleSheet != null) {
            guiForm = guiFormBuilder.buildQLSForm(this.qlForm.getForm(), this.qlsStyleSheet);
        } else {
            guiForm = guiFormBuilder.buildQLForm(this.qlForm.getForm());
        }

        GUIController guiController = new GUIController(this.qlForm);

        // Show form in window and set window title
        this.formArea.getChildren().add(guiForm.render(guiController));
        primaryStage.setTitle(this.qlForm.getIdentifier());
    }

    private MenuBar createMenuBar(Stage primaryStage) {
        Menu fileMenu = new Menu("File");
        MenuItem loadQlFile = new MenuItem("Load QL file");
        MenuItem loadQlsFile = new MenuItem("Load QLS file");
        MenuItem exportResults = new MenuItem("Export results");

        loadQlFile.setOnAction(e -> {
            this.loadQLClicked(primaryStage);
            this.fileLoaded(primaryStage);
        });

        loadQlsFile.setOnAction(e -> {
            this.loadQLSClicked(primaryStage);
            this.fileLoaded(primaryStage);
        });

        exportResults.setOnAction(event -> {
            this.saveClicked(primaryStage);
        });

        fileMenu.getItems().addAll(loadQlFile, loadQlsFile, exportResults);
        return new MenuBar(fileMenu);
    }

    private void loadQLClicked(Stage primaryStage) {
        FileChooser fileChooser = this.getFileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QL File (*.ql)", "*.ql"));
        this.qlFile = fileChooser.showOpenDialog(primaryStage);
    }

    private void loadQLSClicked(Stage primaryStage) {
        FileChooser fileChooser = this.getFileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QLS File (*.qls)", "*.qls"));
        this.qlsFile = fileChooser.showOpenDialog(primaryStage);
    }

    private void saveClicked(Stage primaryStage) {
        if(this.qlForm == null) {
            this.showWarningAlert("Cannot export results as no form is loaded");
            return;
        }

        FileChooser fileChooser = this.getFileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt"));
        File exportFile = fileChooser.showSaveDialog(primaryStage);

        if (exportFile == null) {
            return;
        }

        try {
            this.qlForm.exportResults(exportFile);
        } catch (IOException e) {
            this.showErrorAlert(e, "Unable to export results to selected file location");
        }
    }

    private FileChooser getFileChooser() {
        // Open file chooser on user's Desktop folder
        FileChooser fileChooser = new FileChooser();
        String homeDirectory = System.getProperty("user.home") + "/Desktop";
        fileChooser.setInitialDirectory(new File(homeDirectory));
        return fileChooser;
    }

    private void handleQLError(Exception e, String message) {
        // Error, so do not keep form in memory
        this.qlForm = null;
        this.qlFile = null;
        this.showErrorAlert(e, message);
    }

    private void handleQLSError(Exception e, String message) {
        // Error, so do not keep stylesheet in memory
        this.qlsStyleSheet = null;
        this.qlsFile = null;
        this.showErrorAlert(e, message);
    }

    private void showErrorAlert(Exception e, String message) {
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
