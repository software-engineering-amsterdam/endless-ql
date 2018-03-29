package gui;

import gui.builder.GUIFormBuilder;
import gui.model.GUIForm;
import gui.render.GUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import ql.QLFormBuilder;
import ql.SymbolTableExporter;
import ql.analysis.warning.QLWarningAnalyzer;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import qls.QLSFormBuilder;
import qls.model.StyleSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public class Renderer extends Application {
    private StyleSheet qlsStyleSheet;
    private Form qlForm;
    private SymbolTable symbolTable;

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
            QLFormBuilder qlFormBuilder = new QLFormBuilder();
            this.qlForm = qlFormBuilder.buildForm(new FileInputStream(qlFile));
            this.symbolTable = qlFormBuilder.getSymbolTable();

            // Check for warning messages
            QLWarningAnalyzer qlWarningAnalyzer = new QLWarningAnalyzer();
            Set<String> warnings = qlWarningAnalyzer.analyze(this.qlForm, this.symbolTable);
            if (!warnings.isEmpty()) {
                showWarningAlert(String.join("\n", warnings));
            }

            QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(this.qlForm, this.symbolTable);
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
        try {
            System.out.println("exporting1");
            SymbolTableExporter.export(symbolTable, formExportFile);
        } catch (FileNotFoundException e) {
            showErrorAlert(e, "Could not write to export file, check file permissions.");
        }

        primaryStage.show();
    }

    public void buildQuestions(Stage stage) {
        // Set locale to US such that DecimalFormat, such as in a spinner, always uses dots instead of commas
        Locale.setDefault(Locale.US);

        GUIFormBuilder guiFormBuilder = new GUIFormBuilder();

        GUIForm guiForm;
        if(this.qlsStyleSheet != null){
            guiForm = guiFormBuilder.buildQLSForm(this.qlForm, this.qlsStyleSheet);
        } else {
            guiForm = guiFormBuilder.buildQLForm(this.qlForm);
        }

        GUIController guiController = new GUIController(this.symbolTable);

        Scene scene = new Scene(guiForm.render(guiController));
        stage.setTitle(qlForm.getIdentifier() + " form");
        stage.setScene(scene);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.show();
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
