import expression.ReturnType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import model.Form;
import model.Question;
import model.stylesheet.StyleSheet;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.field.Field;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Renderer {

    private final Form form;
    private final StyleSheet styleSheet;

    Renderer(File file) {
        this.form  = parseForm(file);

        File styleSheetFile = new File(file.getParentFile().getAbsolutePath() + "/example.qls");
        this.styleSheet = parseStyleSheet(styleSheetFile);
    }

    private Form parseForm(File formFile){
        Form parseForm = null;

        // Try to parse a stylesheet, if it fails then show an error
        try {
            parseForm = FormParser.parseForm(new FileInputStream(formFile));
        } catch (FileNotFoundException e) {
            showErrorAlert(e, "Form file not found");
        } catch (IOException e) {
            showErrorAlert(e, "Form file not readable, check permissions");
        } catch (UnsupportedOperationException e) {
            // TODO Explain why form is invalid
            showErrorAlert(e, "Form invalid");
        }

        return parseForm;
    }

    private StyleSheet parseStyleSheet(File styleSheetFile){
        StyleSheet parseStyleSheet = null;
        if(styleSheetFile != null){
            // Try to parse a form, if it fails then show an error
            try {
                parseStyleSheet = StyleSheetParser.parseStyleSheet(new FileInputStream(styleSheetFile));
            } catch (FileNotFoundException e) {
                showErrorAlert(e, "StyleSheet file not found");
            } catch (IOException e) {
                showErrorAlert(e, "StyleSheet file not readable, check permissions");
            } catch (UnsupportedOperationException e) {
                // TODO Explain why form is invalid
                showErrorAlert(e, "StyleSheet invalid");
            }
        }
        return parseStyleSheet;
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
        addStatements(fieldMap, fieldGroup, form.questions);
        updateFields(fieldMap, form.questions);
        return fieldGroup;
    }

    private void addQuestion(HashMap<Question, Field> fieldMap, FieldGroup fieldGroup, Question question) {
        Control input;
        TextFormatter formatter;

        System.out.println("debug: " + question.name + " " + question.type);

        switch(question.type){
            case BOOLEAN:
                // Checkbox
                input = createBooleanField(fieldMap, question);
                break;
            case STRING:
                input = createTextField(fieldMap, question, question.type);
                break;
            case INTEGER:
            case DECIMAL:
            case MONEY:
            case NUMBER:
                input = createTextField(fieldMap, question, question.type);
                break;
            case DATE:
                // Date picker
                input = createDateField(fieldMap, question);
                break;
            default:
                throw new UnsupportedOperationException("Cannot create field for unknown field type");
        }

        if(input != null){
            Field field = new Field(question.text, input);
            fieldGroup.add(field);
            fieldMap.put(question, field);
        }
    }

//    private Control createDecimalField(HashMap<Question, Field> fieldMap, Question question) {
//
//    }

    private Control createDateField(HashMap<Question, Field> fieldMap, Question question) {
        DatePicker datePicker = new DatePicker();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            question.answer.setValue(newValue.toString());
            updateFields(fieldMap, form.questions);
        });
//        throw new NotImplementedException();
        return datePicker;
    }

    private Control createBooleanField(HashMap<Question, Field> fieldMap, Question question) {
        CheckBox checkBox = new CheckBox();

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            question.answer.setValue(newValue.toString());
            updateFields(fieldMap, form.questions);
        });

        return checkBox;
    }

    private Control createTextField(HashMap<Question, Field> fieldMap, Question question, ReturnType type) {
        TextInputControl textField = Input.textField();

        if(!question.answer.isSettable()) {
            textField.setEditable(false);
            textField.setText(question.evaluateAnswer());
        }

        // If input changes some questions might need to be enabled/disabled
        textField.setOnKeyTyped(e -> {
            if (textField.isEditable() || !textField.isDisabled()) {
                question.answer.setValue(textField.getText());
                updateFields(fieldMap, form.questions);
            }
        });

        // Add input formatters
        switch(type){
            case INTEGER:
                TextFormatter intFormatter = createTextFormatter("-?\\d*");
                textField.setTextFormatter(intFormatter);
                break;
            case DECIMAL:
                TextFormatter decimalFormatter = createTextFormatter("-?\\d*(\\.\\d*)?");
                textField.setTextFormatter(decimalFormatter);
                break;
            case DATE:
                // TODO isn't date always a datepicker?, or sometimes also a textfield?
//                textField.setTextFormatter(new TextFormatter<>(new DateStringConverter("dd/MM/yyyy")));
                TextFormatter dateFormatter = createTextFormatter("\\d{0,2}?/?\\d{0,2}?/\\d{0,4}?");
                textField.setTextFormatter(dateFormatter);
                break;
            case MONEY:
                TextFormatter moneyFormatter = createTextFormatter("-?\\d*(\\.\\d{0,2})?");
                textField.setTextFormatter(moneyFormatter);
                break;
        }

        return textField;
    }

    private TextFormatter createTextFormatter(String pattern){
        Pattern decimalPattern = Pattern.compile(pattern);
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (decimalPattern.matcher(c.getControlNewText()).matches()) {
                return c;
            } else {
                return null ;
            }
        };
        return new TextFormatter<>(filter);
    }

    private void addStatements(HashMap<Question, Field> fieldMap, FieldGroup fieldGroup, List<Question> questions) {
        for (Question question : questions) {
            addQuestion(fieldMap, fieldGroup, question);
        }
    }

    private Button createSubmitButton(Form form) {
        // TODO save answers to file
        Button submitButton = new Button("Submit (see output in console)");
        submitButton.setOnAction(e -> {
            // Debug output, shows answer to every question in console
            System.out.println();
            for (Question question: form.questions) {
                System.out.println(question.name + " " + question.evaluateAnswer());
            }
        });
        return submitButton;
    }

    private void updateFields(HashMap<Question, Field> fieldMap, List<Question> questions) {
        for (Question question : questions) {
            updateField(fieldMap, question, question.isVisible());
        }
    }

    private void updateField(HashMap<Question, Field> fieldMap, Question question, boolean visible) {
        Field field = fieldMap.get(question);
        field.getLabel().setVisible(visible);
        field.getControl().setVisible(visible);

        if(!question.answer.isSettable()) {
            // TODO: move somwhere else
            Object answer = question.evaluateAnswer();

            if (question.type == ReturnType.BOOLEAN) {
                CheckBox checkBox = (CheckBox) field.getControl();
                checkBox.setSelected(Boolean.TRUE.equals(answer));
            } else {
                TextInputControl textField = (TextInputControl) field.getControl();
                textField.setText(answer.toString());
            }
        }
    }

    private void showErrorAlert(Exception e, String message) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setContentText(e.toString());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
