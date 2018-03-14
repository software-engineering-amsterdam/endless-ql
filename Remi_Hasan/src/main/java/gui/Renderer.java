package gui;

import gui.model.BaseQuestion;
import gui.model.StringQuestion;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import ql.QLFormBuilder;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;
import qls.StyleSheetParser;
import qls.model.StyleSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Renderer extends Application {
    @Override
    public void start(Stage primaryStage) {
        File qlFile = new File(getClass().getResource("../java/example.ql").getFile());
        File qlsFile = new File(getClass().getResource("../java/example.qls").getFile());

        Form qlForm;
        SymbolTable symbolTable;
        StyleSheet qlsStyleSheet;
        try {
            QLFormBuilder qlFormBuilder = new QLFormBuilder();
            qlForm = qlFormBuilder.buildForm(new FileInputStream(qlFile));
            symbolTable = qlFormBuilder.getSymbolTable();
            qlsStyleSheet = StyleSheetParser.parseStyleSheet(new FileInputStream(qlsFile));
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

        buildQuestions(qlForm, primaryStage);

        primaryStage.show();
    }

    private void buildQuestions(Form qlForm, Stage stage) {
        List<BaseQuestion> formQuestions = new ArrayList<>();
        for (Question q : qlForm.questions) {
            formQuestions.add(new StringQuestion(q.text, q.condition, !q.isComputed()));
        }

        // Show all fields
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        for (BaseQuestion formQuestion : formQuestions) {
            vBox.getChildren().add(formQuestion.render());
        }

        Scene scene = new Scene(vBox);
        stage.setTitle(qlForm.identifier + " form");
        stage.setScene(scene);
        stage.show();
    }

    private void showErrorAlert(Exception e, String message) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
