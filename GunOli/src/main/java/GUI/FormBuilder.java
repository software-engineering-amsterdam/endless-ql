package GUI;

import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.*;
import QL.AST.Question;
import QL.Evaluation.EvaluationVisitor;
import QL.Evaluation.Value;
import QL.Evaluation.Values.BooleanValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import QL.AST.Form;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class FormBuilder {
    private Form form;
    private Stage stage;
    private EvaluationVisitor evaluationVisitor;

    public FormBuilder(Form form, Stage stage){
        setForm(form);
        setStage(stage);
        evaluationVisitor = new EvaluationVisitor(form);
    }

    public void setForm(Form form){
        this.form = form;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initializeFormGrid(GridPane formGrid){
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(25,25,25,25));
        //formGrid.setGridLinesVisible(true); // for debugging
    }

    public void renderForm(){
        GridPane formGrid = new GridPane();
        int fieldRow = 0;

        initializeFormGrid(formGrid);
        fieldRow = createFormTitle(fieldRow, formGrid);
        fieldRow = renderFormQuestions(fieldRow, formGrid);

        VBox submitButtonGroup = createSubmitButtonGroup();
        formGrid.add(submitButtonGroup, 1, fieldRow);

        Scene scene = new Scene(formGrid);
        stage.setScene(scene);
        stage.show();
    }

    private int createFormTitle(int fieldRow, GridPane formGrid){
        int currentRow = fieldRow;
        Text formTitle = new Text(form.getName());
        formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        formGrid.add(formTitle, 0, currentRow);
        currentRow++;
        return currentRow;
    }

    private int renderFormQuestions(int fieldRow, GridPane formGrid){
        int currentRow = fieldRow;
        for(Question question : form.getQuestions()){
            Label questionLabel = new Label(question.getText());
            Control questionField = createQuestionField(question);
            BooleanValue enabled = (BooleanValue) question.getCondition().accept(evaluationVisitor);

            questionLabel.setVisible(enabled.getValue());
            questionField.setVisible(enabled.getValue());
            formGrid.add(questionLabel, 0, currentRow);
            formGrid.add(questionField, 1, currentRow);
            currentRow++;
        }
        return currentRow;
    }

    private VBox createSubmitButtonGroup(){
        Button submitBtn = new Button("Submit");
        Text actiontarget = new Text();

        submitBtn.setOnAction((event) -> {
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText("Submit button pressed."); //Todo: Change after debugging?
        });

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.getChildren().add(submitBtn);
        vBox.getChildren().add(actiontarget);
        return vBox;
    }

    private Control createQuestionField(Question question){
        String format = "";
        switch(question.getType()){
            case String:
                return createInputField(question, format);
            case Integer:
                format = "-?\\d*";
                return createInputField(question, format);
            case Decimal:
                format = "-?\\d*(\\.\\d*)?";
                return createInputField(question, format);
            case Money:
                format = "-?\\d*(\\.\\d{0,2})?";
                return createInputField(question, format);
            case Boolean:
                return createBoolField(question);
            case Date:
                return createDateField(question);
            default:
                return null;// Change
        }
    }

    private Control createInputField(Question question, String format){
        TextField textField = new TextField();
        BooleanValue enabled = (BooleanValue) question.getCondition().accept(evaluationVisitor);
        textField.setEditable(enabled.getValue());
        textField.setDisable(question.isPredefined());

        String answerString = evaluationVisitor.evaluateQuestion(question);
        textField.setText(answerString);

        if(!format.isEmpty()) {
            textField.setTextFormatter(createInputFormat(format));
        }

        textField.focusedProperty().addListener((observableFocus, outFocus, inFocus) -> {
            if(inFocus){
                textField.textProperty().addListener((observableText, oldValue, newValue) -> {
                    if(!textField.isDisabled() && !textField.getText().isEmpty()){
                        Expression newAnswer = createNewAnswer(question.getType(), newValue, question.getLineNumber());
                        form.getExpressionTable().updateExpression(question.getIdentifier(), newAnswer);
                    }});
            } else {
                renderForm();
            }
        });

        return textField;
    }

    private Control createBoolField(Question question){
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(Boolean.valueOf(evaluationVisitor.evaluateQuestion(question)));
        checkBox.setDisable(question.isPredefined());

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(!checkBox.isDisabled()){
                    Expression newAnswer = new BooleanConstant(newValue, question.getLineNumber());
                    form.getExpressionTable().updateExpression(question.getIdentifier(), newAnswer);
                    renderForm();
                }
            });
        return checkBox;
    }

    private Control createDateField(Question question){
        DatePicker datePicker = new DatePicker();
        String answerString = evaluationVisitor.evaluateQuestion(question);
        LocalDate currentAnswer = LocalDate.parse(answerString);
        datePicker.setValue(currentAnswer);
        datePicker.setDisable(question.isPredefined());

        datePicker.valueProperty().addListener((observable, oldValue, newValue)->{
            Expression newAnswer = new DateConstant(newValue, question.getLineNumber());
            form.getExpressionTable().updateExpression(question.getIdentifier(), newAnswer);
            renderForm();
        });
        return datePicker;
    }

    private Constant createNewAnswer(EvaluationType type, String answer, int line){
        switch (type){
            case Integer:
                return new IntegerConstant(Integer.parseInt(answer), line);
            case Decimal:
                return new DecimalConstant(Double.parseDouble(answer), line);
            case Money:
                return new MoneyConstant(Double.parseDouble(answer), line);
            default:
                return new StringConstant(answer, line);
        }
    }

    private TextFormatter createInputFormat(String inputFormat){
        Pattern inputPattern = Pattern.compile(inputFormat);
        UnaryOperator<TextFormatter.Change> format = change -> {
            return inputPattern.matcher(change.getControlNewText()).matches() ? change : null;
        };
        return new TextFormatter(format);
    }
}
