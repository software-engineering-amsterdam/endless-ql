package gui;

import gui.model.GUIForm;
import gui.model.GUIInterface;
import gui.widgets.GUIWidget;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import ql.QLFormBuilder;
import ql.analysis.SymbolTable;
import ql.model.Form;
import qls.QLSFormBuilder;
import qls.model.StyleSheet;

import javax.swing.event.ChangeListener;
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
        File qlFile = new File(getClass().getResource("../java/example.ql").getFile());
        File qlsFile = new File(getClass().getResource("../java/example.qls").getFile());

        try {
            QLFormBuilder qlFormBuilder = new QLFormBuilder();
            this.qlForm = qlFormBuilder.buildForm(new FileInputStream(qlFile));
            this.symbolTable = qlFormBuilder.getSymbolTable();

            // Check for warning messages
            Set<String> warnings = qlFormBuilder.getWarnings(this.qlForm);
            if(!warnings.isEmpty()) {
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

        primaryStage.show();
    }

    public void buildQuestions(Stage stage) {
        // Set locale to US such that DecimalFormat, such as in a spinner, always uses dots instead of commas
        Locale.setDefault(Locale.US);

        GUIForm guiForm;
        if(this.qlsStyleSheet == null){
            guiForm = GUIFormBuilder.buildQLForm(this.qlForm);
        } else {
            guiForm = GUIFormBuilder.buildQLSForm(this.qlForm, this.qlsStyleSheet);
            guiForm.update(qlsStyleSheet);
        }
        guiForm.update(symbolTable);
        guiForm.setChangeListener(e -> {
            guiForm.update(symbolTable);
            System.out.println(symbolTable);
        });

        Scene scene = new Scene(guiForm.render());
        stage.setTitle(qlForm.identifier + " form");
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
