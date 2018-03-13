package GUI;

import ParseObjectsQL.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import ParseObjectsQL.Form;
import ParseObjectsQL.QuestionMap;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FormBuilder {
    private Form form;
    private QuestionMap questionMap;
    private Stage stage;
    private GridPane formGrid;
    private int fieldRow;

    public FormBuilder(Form form, QuestionMap questionMap, Stage stage){
        setForm(form);
        setQuestionMap(questionMap);
        setStage(stage);

        formGrid = new GridPane();
        fieldRow = 0;
        initializeFormGrid();
    }

    public void setForm(Form form){
        this.form = form;
    }

    public void setQuestionMap(QuestionMap questionMap){
        this.questionMap = questionMap;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initializeFormGrid(){
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(25,25,25,25));
        //formGrid.setGridLinesVisible(true); // for debugging
    }

    public void renderForm(){
        createFormTitle();
        renderFormQuestions();

        VBox submitButtonGroup = createSubmitButtonGroup();
        formGrid.add(submitButtonGroup, 1, fieldRow);

        Scene scene = new Scene(formGrid);
        stage.setScene(scene);
        stage.show();
    }

    private void createFormTitle(){
        Text formTitle = new Text(form.getName());
        formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        formGrid.add(formTitle, 0, fieldRow);
        fieldRow++;
    }

    private void renderFormQuestions(){
        for(Question q : form.getBlock().getQuestions()){
            Question question = questionMap.getQuestion(q.getIdentifier());
            Label questionLabel = new Label(question.getText());
            Control questionField = createQuestionField(question);
            formGrid.add(questionLabel, 0, fieldRow);
            formGrid.add(questionField, 1, fieldRow);
            fieldRow++;
        }
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
        switch(question.getType()){
            case String:
                return createStringField();
            case Integer:
                return createIntField();
            case Decimal:
                return createDecimalField();
            case Money:
                return createMoneyField();
            case Boolean:
                return createBoolField();
            case Date:
                return createDateField();
            default:
                return null;// Change
        }
    }

    private Control createStringField(){
        TextField textField = new TextField();
        return textField;
    }

    private Control createIntField(){
        return new TextField();
    }

    private Control createDecimalField(){
        return new TextField();
    }

    private Control createMoneyField(){
        return new TextField();
    }

    private Control createBoolField(){
        CheckBox checkBox = new CheckBox();
        return checkBox;
    }

    private Control createDateField(){
        return new DatePicker();
    }

}
