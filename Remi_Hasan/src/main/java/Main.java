import answer.AnswerBoolean;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Block;
import model.Condition;
import model.Form;
import model.Question;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.control.option.OptionList;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.InputStream;
import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Parse form from specified file
        String fileName = "example.ql";
        InputStream stream = getClass().getResourceAsStream(fileName);
        Form form = FormParser.parseForm(stream);

        // Inspired by https://github.com/YoriChan/FormFX

        // Create a group of fields composed of our form questions
        FieldGroup fieldGroup = createFieldGroup(form);

        // Create our form grid
        GridForm gridForm = new GridForm(fieldGroup);
        gridForm.setPadding(new Insets(10, 10, 10, 10));
        gridForm.setFieldOrientation(Orientation.HORIZONTAL);
        gridForm.setAlignment(Pos.CENTER);

        // Build submit button
        Button submitButton = createSubmitButton();

        // Create box with form and submit button
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridForm, submitButton);

        // Create entire scene
        Scene scene = new Scene(vBox);
        stage.setTitle(form.identifier + " form");
        stage.setScene(scene);
        stage.show();
    }

    private FieldGroup createFieldGroup(Form form){
        FieldGroup fieldGroup = new FieldGroup();
        HashMap<String, Control> fields = new HashMap<>();
        addQuestionsToFieldGroup(fields, form, form.block, fieldGroup);
        changeEditableFields(fields, form, form.block, true);

        return fieldGroup;
    }

    private void addQuestionsToFieldGroup(HashMap<String, Control> fields, Form form, Block block, FieldGroup fieldGroup){
        for(Question question : block.questions) {
            // TODO, change type of question based on question.answer


            if(question.answer instanceof AnswerBoolean){
                ComboBox<String> input = Input.comboBox(
                        new OptionList() {{
                            add("", true);
                            add("true");
                            add("false");
                        }});

                // If input changes some questions might need to be enabled/disabled
                input.setOnAction(e -> {
                    if(input.isEditable() || !input.isDisabled()){
                        changeQuestionAnswer(input, block);
                        changeEditableFields(fields, form, block, true);
                    }
                });

                fields.put(question.name, input);
                fieldGroup.join(question.name, question.text, input);
            }
            else {
                TextInputControl input = Input.textField("");

                // If input changes some questions might need to be enabled/disabled
                input.setOnKeyTyped(e -> {
                    if(input.isEditable()){
                        changeQuestionAnswer(input, block);
                        changeEditableFields(fields, form, block, true);
                    }

                    System.out.println(form);
                });

                fields.put(question.name, input);
                fieldGroup.join(question.name, question.text, input);
            }

            // separator, might be useful to visually make groups apparent
//            fieldGroup.separate();


        }
        for(Condition condition : block.conditions){
            addQuestionsToFieldGroup(fields, form, condition.block, fieldGroup);
        }
    }

    private void changeQuestionAnswer(TextInputControl field, Block block) {
        block.answer(field.getId(), field.getText());
    }

    private void changeQuestionAnswer(ComboBox field, Block block) {
        block.answer(field.getId(), field.getSelectionModel().getSelectedItem().toString());
    }

    private void changeEditableFields(HashMap<String, Control> fields, Form form, Block block, boolean inEditableBlock) {
        for(Question question : block.questions){
            Control field = fields.get(question.name);
            // TODO implement more field types
            if(field instanceof ComboBox){
                ComboBox<String> comboBoxField = (ComboBox) field;
                comboBoxField.setDisable(!inEditableBlock);
                // If the condition was not met, we should make it reset to default
                if(!inEditableBlock){
                    comboBoxField.getSelectionModel().clearSelection();
                }
            }
        }
        for(Condition condition : block.conditions){
            // Check if the expression of this block is met
            boolean isEditableSubBlock = inEditableBlock && Boolean.TRUE.equals(condition.expression.evaluate(form));

            changeEditableFields(fields, form, condition.block, isEditableSubBlock);
        }
    }

    private Button createSubmitButton(){
        // TODO change to filling out the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            System.out.println("Submit called");
        });
        return submitButton;
    }
}
