package gui;

import gui.formfx.control.Input;
import gui.formfx.field.Field;
import gui.formfx.field.FieldGroup;
import gui.formfx.form.GridForm;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ql.analysis.Lookup;
import ql.analysis.ReferencedIdentifiersVisitor;
import ql.evaluation.Binding;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FormRenderer {

    private final Form form;
    private List<Binding> bindings;

    public FormRenderer(Form form) {
        this.form = form;
        ReferencedIdentifiersVisitor referencedIdentifiersVisitor = new ReferencedIdentifiersVisitor();
        this.bindings = referencedIdentifiersVisitor.getBindings(form);
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

        switch (question.type) {
            case BOOLEAN:
                input = createBooleanField(fieldMap, question);
                break;
            case STRING:
                input = createTextField(fieldMap, question);
                break;
            case INTEGER:
                input = createIntField(fieldMap, question);
                break;
            case DECIMAL:
                input = createDecimalField(fieldMap, question);
                break;
            case MONEY:
                input = createMoneyField(fieldMap, question);
                break;
            case DATE:
                input = createDateField(fieldMap, question);
                break;
            default:
                throw new UnsupportedOperationException("Cannot create field for unknown field type");
        }

        if (input != null) {
            Field field = new Field(question.text, input);
            fieldGroup.add(field);
            fieldMap.put(question, field);
        }
    }

    // TODO make nicer
    private void setExpression(String name, Expression expression){

        // TODO remove debug
        System.out.println();
        for(Binding binding : bindings){
            System.out.println(binding);
        }

        // Remove bindings with this name
        bindings = bindings.stream().filter(x -> !x.name.equals(name)).collect(Collectors.toList());

        Binding binding = new Binding(name, expression);
        bindings.add(0, binding);
    }

    private Control createDateField(HashMap<Question, Field> fieldMap, Question question) {
        DatePicker datePicker = new DatePicker();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            Expression expression = new ExpressionVariableDate(question.defaultAnswer.getToken(), Date.from(newValue.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            setExpression(question.name, expression);
            updateFields(fieldMap, form.questions);
        });
        return datePicker;
    }

    private Control createBooleanField(HashMap<Question, Field> fieldMap, Question question) {
        CheckBox checkBox = new CheckBox();
        checkBox.setDisable(question.isComputed());
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!checkBox.isDisabled()){
                Expression expression = new ExpressionVariableBoolean(question.defaultAnswer.getToken(), Boolean.parseBoolean(newValue.toString()));
                setExpression(question.name, expression);
                updateFields(fieldMap, form.questions);
            }
        });

        return checkBox;
    }

    private Control createTextField(HashMap<Question, Field> fieldMap, Question question) {
        TextInputControl textField = Input.textField();
        textField.setEditable(!question.isComputed());

        // If input changes some questions might need to be enabled/disabled
        textField.setOnKeyTyped(e -> {
            if (textField.isEditable() || !textField.isDisabled()) {
                Expression expression = new ExpressionVariableString(question.defaultAnswer.getToken(),
                        textField.getText());
                setExpression(question.name, expression);
                updateFields(fieldMap, form.questions);
            }
        });

        return textField;
    }

    private Control createIntField(HashMap<Question, Field> fieldMap, Question question) {
        TextInputControl textField = Input.textField();
        textField.setEditable(!question.isComputed());

        // If input changes some questions might need to be enabled/disabled
        textField.setOnKeyTyped(e -> {
            if (textField.isEditable() || !textField.isDisabled()) {
                Expression expression = new ExpressionVariableUndefined(question.defaultAnswer.getToken(), question.type);
                if (!textField.getText().isEmpty()) {
                    expression = new ExpressionVariableInteger(question.defaultAnswer.getToken(),
                            Integer.parseInt(textField.getText()));
                    TextFormatter intFormatter = createTextFormatter("-?\\d*");
                    textField.setTextFormatter(intFormatter);
                }

                setExpression(question.name, expression);
                updateFields(fieldMap, form.questions);
            }
        });

        // Add input formatter
        TextFormatter intFormatter = createTextFormatter("-?\\d*");
        textField.setTextFormatter(intFormatter);

        return textField;
    }

    private Control createDecimalField(HashMap<Question, Field> fieldMap, Question question) {
        TextInputControl textField = Input.textField();
        textField.setEditable(!question.isComputed());

        // If input changes some questions might need to be enabled/disabled
        textField.setOnKeyTyped(e -> {
            if (textField.isEditable() || !textField.isDisabled()) {
                Expression expression = new ExpressionVariableUndefined(question.defaultAnswer.getToken(), question.type);
                if (!textField.getText().isEmpty()) {
                    expression = new ExpressionVariableDecimal(question.defaultAnswer.getToken(),
                            Double.parseDouble(textField.getText()));
                }

                setExpression(question.name, expression);
                updateFields(fieldMap, form.questions);
            }
        });

        // Add input formatter
        TextFormatter decimalFormatter = createTextFormatter("-?\\d*(\\.\\d*)?");
        textField.setTextFormatter(decimalFormatter);

        return textField;
    }

    private Control createMoneyField(HashMap<Question, Field> fieldMap, Question question) {
        TextInputControl textField = Input.textField();
        textField.setEditable(!question.isComputed());

        // If input changes some questions might need to be enabled/disabled
        textField.setOnKeyTyped(e -> {
            if (textField.isEditable() || !textField.isDisabled()) {
                Expression expression = new ExpressionVariableUndefined(question.defaultAnswer.getToken(), question.type);
                if (!textField.getText().isEmpty()) {
                    expression = new ExpressionVariableString(question.defaultAnswer.getToken(), textField.getText());
                }

                setExpression(question.name, expression);
                updateFields(fieldMap, form.questions);
            }
        });

        // Add input formatter
        TextFormatter moneyFormatter = createTextFormatter("-?\\d*(\\.\\d{0,2})?");
        textField.setTextFormatter(moneyFormatter);

        return textField;
    }

    private TextFormatter createTextFormatter(String pattern) {
        Pattern textPattern = Pattern.compile(pattern);
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (textPattern.matcher(c.getControlNewText()).matches()) {
                return c;
            } else {
                return null;
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
        submitButton.setOnAction((ActionEvent e) -> {
            // Debug output, shows answer to every question in console
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            Set<String> printedBindings = new HashSet<>();
            for(Binding binding : bindings){
                if(!printedBindings.contains(binding.name)){
                    printedBindings.add(binding.name);
                    Value value = binding.expression.accept(evaluator, bindings);
                    System.out.println(binding.name + " " + value);
                }
            }
        });
        return submitButton;
    }

    private void updateFields(HashMap<Question, Field> fieldMap, List<Question> questions) {
        for (Question question : questions) {
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            Boolean value = evaluator.visit(question.condition, bindings).getBooleanValue();
            updateField(fieldMap, question, value);
        }
    }

    private void updateField(HashMap<Question, Field> fieldMap, Question question, boolean visible) {
        Field field = fieldMap.get(question);
        field.getLabel().setVisible(visible);
        field.getControl().setVisible(visible);

        // TODO cleanup
        if(!visible){
            if (question.type == ReturnType.BOOLEAN) {
                CheckBox checkBox = (CheckBox) field.getControl();
                checkBox.setSelected(false);
            } else if (question.type == ReturnType.DATE) {
                // TODO
            } else {
                TextInputControl textField = (TextInputControl) field.getControl();
                textField.setText("");
            }
        }

        // If question is based on value and cannot be set by the user, set value by evaluating its value
        if (question.isComputed()) {
//            String answer = symbolTable.getStringValue(question.name, question.type);
            ExpressionEvaluator evaluator = new ExpressionEvaluator();
            Value answer = Lookup.lookup(question.name, bindings).accept(evaluator, bindings);

            if (question.type == ReturnType.BOOLEAN) {
                CheckBox checkBox = (CheckBox) field.getControl();
                checkBox.setSelected(Boolean.valueOf(answer.toString()));
            } else if (question.type == ReturnType.DATE) {
                if(!answer.isUndefined()) {
                    DatePicker datePicker = (DatePicker) field.getControl();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
                    datePicker.setValue(LocalDate.parse(answer.toString(), formatter));
                }
            } else {
                TextInputControl textField = (TextInputControl) field.getControl();
                textField.setText(answer.toString());
            }
        }
    }
}
