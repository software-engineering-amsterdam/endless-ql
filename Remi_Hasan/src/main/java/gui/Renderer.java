package gui;

import gui.model.*;
import gui.observers.GUIQuestionObserver;
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
            this.qlsStyleSheet = StyleSheetParser.parseStyleSheet(new FileInputStream(qlsFile));
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

    private void buildQuestions(Form form, Stage stage) {
        List<GUIQuestion> guiQuestions = new ArrayList<>();

        List<GUIQuestionObserver> observers = new ArrayList<>();

        for (Question question : form.questions) {
            GUIQuestion guiQuestion;
            switch (question.type) {
                case INTEGER:
                    guiQuestion = new GUIIntegerQuestion(question.name, question.text, question.condition, question.isComputed());
                    break;
                case DECIMAL:
                    guiQuestion = new GUIDecimalQuestion(question.name, question.text, question.condition, question.isComputed());
                    break;
                case MONEY:
                    guiQuestion = new GUIMoneyQuestion(question.name, question.text, question.condition, question.isComputed());
                    break;
                case STRING:
                    guiQuestion = new GUIStringQuestion(question.name, question.text, question.condition, question.isComputed());
                    break;
                case DATE:
                    guiQuestion = new GUIDateQuestion(question.name, question.text, question.condition, question.isComputed());
                    break;
                case BOOLEAN:
                    guiQuestion = new GUIBooleanQuestion(question.name, question.text, question.condition, question.isComputed());
                    break;
                default:
                    throw new UnsupportedOperationException("Data type " + question.type + " not implemented into GUI renderer");
            }

            observers.add(new GUIQuestionObserver(guiQuestion));
            guiQuestions.add(guiQuestion);
        }

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        for (GUIQuestion guiQuestion : guiQuestions) {
            vBox.getChildren().add(guiQuestion.getWidget().getUI());
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
