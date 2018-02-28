package org.uva.sea.ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.ql.QLCompiler;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class QuestionController implements Initializable {

    private List<QuestionGUI> questionGUIs;
    private List<Question> questions;

    @FXML
    private VBox vBox;

    @FXML
    private void addCheckBox(ActionEvent event) {
        vBox.getChildren().add(createBooleanQuestion("Did you sell a house in 2012?"));
    }

    @FXML
    private void addTextField(ActionEvent event) {
        vBox.getChildren().add(createNumericQuestion("What was the selling price?"));
    }

    @FXML
    public void addLabel(ActionEvent actionEvent) {
        vBox.getChildren().add(createComputedQuestion("You sell a house in: "));
    }

    @FXML
    public void addNewQuestion(ActionEvent actionEvent) {
        vBox.getChildren().add(createQuestionRow(new Label("Did you sell a house?"), new CheckBox()));
    }

    @FXML
    private void printQuestions(ActionEvent actionEvent){
        for (QuestionGUI questionGUI: questionGUIs) {
            vBox.getChildren().add(createQuestionRow(questionGUI));
        }
    }

    private void printQuestions(){
        for (QuestionGUI questionGUI: questionGUIs) {
            if(questionGUI.isShouldBeVisible()) {
                vBox.getChildren().add(createQuestionRow(questionGUI));
            }
        }
    }

    private Node createBooleanQuestion(String question) {
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("Checkbox changed! " + question);
            }
        });
        return createQuestionRow(new Label(question), checkBox);
    }

    private Node createNumericQuestion(String question) {
        TextField textField = new TextField();
        textField.setEditable(true);
        textField.setMinWidth(100.0);
        return createQuestionRow(new Label(question), textField);
    }

    private Node createComputedQuestion(String question) {
        Label label = new Label("2012");
        return createQuestionRow(new Label(question), label);
    }

    private Node createQuestionRow(QuestionGUI questionGUI) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(questionGUI.getLabel(), 0, 0);
        wrapper.add(questionGUI.getType(), 1, 0);

        return wrapper;
    }

    private Node createQuestionRow(final Node label, final Node input) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(label, 0, 0);
        wrapper.add(input, 1, 0);

        return wrapper;
    }

    public List<Question> getSampleQuestion() {

        File folder = new File("/Users/joannaroczniak/Desktop/UvA/endless-ql/Joanna_Laurence/src/main/resources/gui/");
        File file = folder.listFiles()[0];
        System.out.println(file);

        String fileName = file.getAbsolutePath();
        FormEvaluator formEvaluator = new FormEvaluator();
        try {
            QLCompiler compiler = new QLCompiler();
            CharStream steam = CharStreams.fromStream(new FileInputStream(fileName));
            Form result = compiler.compileScriptFile(steam);
            if (result == null)
                return new ArrayList<>();
            List<Question> questions = formEvaluator.evaluate(result, new SymbolTable());
            return questions;
        } catch (IOException e) {
            System.err.println("IO exception");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(location);

        questions = getSampleQuestion();

        for (Question question : questions) {
            System.out.println(question.getLabel() +
                    " " + question.getValue() +
                    " " + question.getVariable().getVariableName() +
                    " " + question.getNodeType().getNodeType().name());
        }

        updateQuestionGUIs();
        printQuestions();
    }

    private void updateQuestionGUIs(){
        this.questionGUIs = new ArrayList<>();
        for (Question question: this.questions) {

            QuestionGUI questionGUI = new QuestionGUI(generateLabel(question.getLabel()), generateInput(question.getNodeType().getNodeType()));
            questionGUI.setShouldBeVisible(true);
            questionGUIs.add(questionGUI);
        }
    }

    private Label generateLabel(String name){
        return new Label(name);
    }

    private Control generateInput(NodeType nodeType){
        switch (nodeType){
            case BOOLEAN: return new CheckBox();
            case DECIMAL:
            case INTEGER:
            case MONEY:
            case STRING:
            case DATE:
                TextField textField = new TextField();
                textField.setEditable(true);
                textField.setMinWidth(100.0);
                return textField;
            case UNKNOWN:
            default:
                return new Label("UNKNOWN");
        }
    }
}
