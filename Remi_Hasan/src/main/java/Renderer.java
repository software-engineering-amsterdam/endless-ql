import expression.ReturnType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Condition;
import model.Form;
import model.Question;
import model.Statement;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.field.Field;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Renderer {

    private final Form form;

    Renderer(File file) {
        Form parseForm = null;
        try {
            parseForm = FormParser.parseForm(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            showErrorAlert(e, "File not found");
        } catch (IOException e) {
            showErrorAlert(e, "File not readable, check permissions");
        } catch (UnsupportedOperationException e) {
            // TODO Explain why form is invalid
            showErrorAlert(e, "Form invalid");
        }

        this.form = parseForm;
    }

    public void renderForm(Stage stage) {
        // Create a group of fields composed of our form questions
        FieldGroup fieldGroup = createQuestionFields(form);

        // Create our form grid
        GridForm gridForm = new GridForm(fieldGroup);
        gridForm.setPadding(new Insets(10, 10, 10, 10));
        gridForm.setFieldOrientation(Orientation.HORIZONTAL);
        gridForm.setAlignment(Pos.CENTER);

        // Build submit button
        Button submitButton = createSubmitButton(form);

        // Create box with questions and submit button
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridForm, submitButton);

        // Create entire scene
        Scene scene = new Scene(vBox);
        stage.setTitle(form.identifier + " form");
        stage.setScene(scene);
        stage.show();
    }

    private FieldGroup createQuestionFields(Form form) {
        FieldGroup fieldGroup = new FieldGroup();
        HashMap<Question, Field> fieldMap = new HashMap<>();
        addStatements(fieldMap, fieldGroup, form.statements);
        updateConditional(fieldMap, form.statements, true);
        return fieldGroup;
    }

    private void addQuestion(HashMap<Question, Field> fieldMap, FieldGroup fieldGroup, Question question) {
        Control input;

        if (question.answer.getReturnType() == ReturnType.Boolean) {
            input = createBooleanField(fieldMap, question);
        } else {
            input = createTextField(fieldMap, question);
        }

        Field field = new Field(question.text, input);
        fieldGroup.add(field);
        fieldMap.put(question, field);
    }

    private Control createBooleanField(HashMap<Question, Field> fieldMap, Question question) {
        CheckBox checkBox = new CheckBox();

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            question.answer.setValue(newValue.toString());
            updateConditional(fieldMap, form.statements, true);
        });

        return checkBox;
    }

    private Control createTextField(HashMap<Question, Field> fieldMap, Question question) {
        TextInputControl textField = Input.textField();

        // If input changes some questions might need to be enabled/disabled
        textField.setOnKeyTyped(e -> {
            if (textField.isEditable() || !textField.isDisabled()) {
                question.answer.setValue(textField.getText());
                updateConditional(fieldMap, form.statements, true);
            }
        });

        return textField;
    }

    private void addStatements(HashMap<Question, Field> fieldMap, FieldGroup fieldGroup, ArrayList<Statement> statements) {
        for (Statement statement : statements) {
            if (statement.isQuestion()) {
                addQuestion(fieldMap, fieldGroup, (Question) statement);
            } else {
                addStatements(fieldMap, fieldGroup, ((Condition) statement).trueStatements);
                addStatements(fieldMap, fieldGroup, ((Condition) statement).falseStatements);
            }
        }
    }

    private Button createSubmitButton(Form form) {
        // TODO save answers to file
        Button submitButton = new Button("Submit (see output in console)");
        submitButton.setOnAction(e -> {
            // Debug output, shows answer to every question in console
            for (Statement statement : form.statements) {
                if (statement.isQuestion()) {
                    System.out.println(((Question) statement).answer);
                }
            }
        });
        return submitButton;
    }

    private void updateConditional(HashMap<Question, Field> fieldMap, ArrayList<Statement> statements, boolean isTrue) {
        for (Statement statement : statements) {
            if (statement.isQuestion()) {
                Field field = fieldMap.get((Question) statement);
                field.getLabel().setVisible(isTrue);
                field.getControl().setVisible(isTrue);
            } else {
                Condition conditional = (Condition) statement;
                boolean trueBlockVisible = isTrue && Boolean.TRUE.equals(conditional.condition.evaluate().get());
                boolean falseBlockVisible = isTrue && Boolean.FALSE.equals(conditional.condition.evaluate().get());
                updateConditional(fieldMap, conditional.trueStatements, trueBlockVisible);
                updateConditional(fieldMap, conditional.falseStatements, falseBlockVisible);
            }
        }
    }

    private void showErrorAlert(Exception e, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setContentText(e.toString());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
