import expression.ReturnType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import model.Condition;
import model.Form;
import model.Question;
import model.Statement;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.control.option.OptionList;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("QL form file selector");

        // Build file selector
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

    private void renderForm(Stage stage, File file) {
        try{
            Form form = FormParser.parseForm(new FileInputStream(file));

            if(form == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "File not found");
                alert.showAndWait();
            }

            // Create a group of fields composed of our form questions
            FieldGroup fieldGroup = createFieldGroup(form);

            // Create our form grid
            GridForm gridForm = new GridForm(fieldGroup);
            gridForm.setPadding(new Insets(10, 10, 10, 10));
            gridForm.setFieldOrientation(Orientation.HORIZONTAL);
            gridForm.setAlignment(Pos.CENTER);

            // Build file selector
            Button fileSelectorButton = createFileSelectorButton(stage);

            // Build submit button
            Button submitButton = createSubmitButton(null);

            // Create box with form and submit button
            VBox vBox = new VBox(35);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(fileSelectorButton, gridForm, submitButton);

            // Create entire scene
            Scene scene = new Scene(vBox);
            stage.setTitle(form.identifier + " form");
            stage.setScene(scene);
            stage.show();
        } catch(FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not found");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not readable, check permissions.");
            alert.showAndWait();
        } catch(IllegalArgumentException e){
            // TODO Explain why form is invalid
            Alert alert = new Alert(Alert.AlertType.ERROR, "Form invalid");
            alert.showAndWait();
        }
    }

    private Button createFileSelectorButton(Stage stage) {
        // Resource path
        File resourcesFile = new File(getClass().getResource("java/example.ql").getFile());
        File resourceDirectory = new File(resourcesFile.getParentFile().getAbsolutePath());

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(resourceDirectory);
        final Button openButton = new Button("Browse files...");

        openButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                renderForm(stage, file);
            }
        });

        return openButton;
    }

    private FieldGroup createFieldGroup(Form form) {
        FieldGroup fieldGroup = new FieldGroup();
        HashMap<String, Control> fields = new HashMap<>();
        addQuestionsToFieldGroup(fields, form, form.statements, fieldGroup);
        changeEditableFields(fields, form.statements, true);

        return fieldGroup;
    }

    private void addQuestionsToFieldGroup(HashMap<String, Control> fields, Form form, ArrayList<Statement> statements, FieldGroup fieldGroup) {
        for (Statement statement : statements) {
            if (statement.isQuestion()) {
                addQuestionsToFieldGroup(fields, form, (Question) statement, fieldGroup);
            } else if (statement.isCondition()) {
                addQuestionsToFieldGroup(fields, form, ((Condition) statement).statements, fieldGroup);
            }
        }
    }

    private void addQuestionsToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        // Only show questions that have answers you can set a value to
        if (question.answer.getReturnType() == ReturnType.Boolean) {
            addBooleanQuestionToFieldGroup(fields, form, question, fieldGroup);
        } else if (question.answer.getReturnType() == ReturnType.Integer || question.answer.getReturnType() == ReturnType.Decimal || question.answer.getReturnType() == ReturnType.String) {
            addNumberQuestionToFieldGroup(fields, form, question, fieldGroup);
        }

        // Test from: https://o7planning.org/en/11185/javafx-spinner-tutorial
//            Label label = new Label("Select Level:");
//            final Spinner<Integer> spinner = new Spinner<Integer>();
//            final int initialValue = 3;
//            // Value factory.
//            SpinnerValueFactory<Integer> valueFactory = //
//                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
//            spinner.setValueFactory(valueFactory);
//            fieldGroup.join("dummy", "dummy2", spinner);

        // separator, might be useful to visually make groups apparent
//            fieldGroup.separate();
    }

    private void addBooleanQuestionToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        ComboBox<String> input = Input.comboBox(
                new OptionList() {{
                    add("", true);
                    add("true");
                    add("false");
                }});

        // TODO implement observer pattern?
        // If input changes some questions might need to be enabled/disabled
        input.setOnAction(e -> {
            if (input.isEditable() || !input.isDisabled()) {
                // Change answer
                changeQuestionAnswer(input, question);
                changeEditableFields(fields, form.statements, true);
            }
        });

        fields.put(question.name, input);
        fieldGroup.join(question.name, question.text, input);
    }

    private void addNumberQuestionToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        TextInputControl input = Input.textField("");

        if (question.answer.getReturnType() == ReturnType.Integer || question.answer.getReturnType() == ReturnType.Decimal) {
            // NumberStringConverter
            // CurrencyStringConverter
            // DoubleStringConverter
            // https://docs.oracle.com/javase/8/javafx/api/javafx/util/StringConverter.html
            input.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        }

        // If input changes some questions might need to be enabled/disabled
        input.setOnKeyTyped(e -> {
            if (input.isEditable()) {
                changeQuestionAnswer(input, question);
                changeEditableFields(fields, form.statements, true);
            }

//                    System.out.println(form);
        });

        fields.put(question.name, input);
        fieldGroup.join(question.name, question.text, input);
    }

    private void addStringQuestionToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        // TODO
    }

    private void changeQuestionAnswer(TextInputControl input, Question question) {
        question.answer.setValue(input.getText());
    }

    //
    private void changeQuestionAnswer(ComboBox input, Question question) {
        question.answer.setValue(input.getSelectionModel().getSelectedItem().toString());
    }

    private void changeEditableFields(HashMap<String, Control> fields, ArrayList<Statement> statements, boolean inEditableBlock) {
        for (Statement statement : statements) {
            changeEditableFields(fields, statement, inEditableBlock);
        }
    }

    private void changeEditableFields(HashMap<String, Control> fields, Statement statement, boolean inEditableBlock) {
        if (statement.isQuestion()) {
            Control field = fields.get(((Question) statement).name);
            field.setVisible(inEditableBlock);
        } else if (statement.isCondition()) {
            changeEditableFields(fields, (Condition) statement, inEditableBlock);
        }
    }

    private void changeEditableFields(HashMap<String, Control> fields, Condition condition, boolean inEditableBlock) {
        boolean inEditableSubBlock = inEditableBlock && Boolean.TRUE.equals(condition.condition.evaluate().get());
        for (Statement statement : condition.statements) {
            changeEditableFields(fields, statement, inEditableSubBlock);
        }
    }

    private Button createSubmitButton(Form form) {
        // TODO save answers to file?
        Button submitButton = new Button("Submit (see output in console)");
        submitButton.setOnAction(e -> {

            // Debug output, shows answer to every question in console
//            form.elements.forEach(x -> printQuestionAnswers(x));
        });
        return submitButton;
    }

    private void printQuestionAnswers(Statement statement) {
        if (statement.isQuestion()) {
            printQuestionAnswers((Question) statement);
        } else if (statement.isCondition()) {
            printQuestionAnswers((Condition) statement);
        }
    }

    private void printQuestionAnswers(Condition condition) {
        for (Statement statement : condition.statements) {
            printQuestionAnswers(statement);
        }
    }


    private void printQuestionAnswers(Question question) {
        System.out.println("\t" + question.name + " => " + question.answer.evaluate());
    }
}