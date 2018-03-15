package gui;

import gui.model.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableString;
import qls.StyleSheetParser;
import qls.model.StyleSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Renderer extends Application {
    private ArrayList<BaseQuestion> formQuestions;
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

    private void somethingChanged(){
        System.out.println("something changed");
        for(BaseQuestion formQuestion : this.formQuestions){
            if(!formQuestion.isComputed){
                String value = formQuestion.getWidget().getValue();
                Expression expression = new ExpressionVariableString(null, value);
                symbolTable.setExpression(formQuestion.name, expression);
            }
        }

        for(BaseQuestion formQuestion : this.formQuestions){
            if(formQuestion.isComputed){
                String value = symbolTable.getStringValue(formQuestion.name, ReturnType.INTEGER);
                System.out.println("setting computed to " + value);
                formQuestion.getWidget().setValue(value);
            }
        }
    }

    private void buildQuestions(Form qlForm, Stage stage) {
        ChangeListener listener = (observable, oldValue, newValue) -> {
            somethingChanged();
        };


        this.formQuestions = new ArrayList<>();
        for (Question question : qlForm.questions) {
            BaseQuestion baseQuestion;

            // TODO: factory
            switch(question.type) {
                case STRING:
                    baseQuestion = new StringQuestion(question.name, question.text, question.computedAnswer, question.isComputed(), listener);
                    break;
                case INTEGER:
                    baseQuestion = new IntegerQuestion(question.name, question.text, question.computedAnswer, question.isComputed(), listener);
                    break;
                case DECIMAL:
                    baseQuestion = new DecimalQuestion(question.name, question.text, question.computedAnswer, question.isComputed(), listener);
                    break;
                case MONEY:
                    baseQuestion = new MoneyQuestion(question.name, question.text, question.computedAnswer, question.isComputed(), listener);
                    break;
                case DATE:
                    baseQuestion = new DateQuestion(question.name, question.text, question.computedAnswer, question.isComputed(), listener);
                    break;
                case BOOLEAN:
                    baseQuestion = new BooleanQuestion(question.name, question.text, question.computedAnswer, question.isComputed(), listener);
                    break;
                default:
                    return;
            }
            formQuestions.add(baseQuestion);
        }

        // Show all fields
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        for (BaseQuestion formQuestion : formQuestions) {
            vBox.getChildren().add(formQuestion.getWidget().getUI());
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
