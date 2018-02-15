import expression.ReturnType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import model.Block;
import model.BlockElement;
import model.Form;
import model.Question;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.control.option.OptionList;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.InputStream;
import java.util.ArrayList;
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
        Button submitButton = createSubmitButton(form);

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
        addQuestionsToFieldGroup(fields, form, form.elements, fieldGroup);
        changeEditableFields(fields, form, form.elements, true);

        return fieldGroup;
    }

    private void addQuestionsToFieldGroup(HashMap<String, Control> fields, Form form, ArrayList<BlockElement> elements, FieldGroup fieldGroup){
//        for(Question question : block.questions) {
//
//            // Only show questions that have answers you can set a value to
//           if(question.answer.isSetable(form)){
//
//               if(question.answer.getReturnType(form) == ReturnType.Boolean){
//                   ComboBox<String> input = Input.comboBox(
//                           new OptionList() {{
//                               add("", true);
//                               add("true");
//                               add("false");
//                           }});
//
//                   // TODO implement observer pattern?
//                   // If input changes some questions might need to be enabled/disabled
//                   input.setOnAction(e -> {
//                       if(input.isEditable() || !input.isDisabled()){
//                           changeQuestionAnswer(input, block);
//                           changeEditableFields(fields, form, block, true);
//                       }
//                   });
//
//                   fields.put(question.name, input);
//                   fieldGroup.join(question.name, question.text, input);
//               }
//               else if(question.answer.getReturnType(form) == ReturnType.Number || question.answer.getReturnType(form) == ReturnType.String){
//                   TextInputControl input = Input.textField("");
//
//                   if(question.answer.getReturnType(form) == ReturnType.Number){
//                       // NumberStringConverter
//                       // CurrencyStringConverter
//                       // DoubleStringConverter
//                       // https://docs.oracle.com/javase/8/javafx/api/javafx/util/StringConverter.html
//                       input.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
//                   }
//
//                   // If input changes some questions might need to be enabled/disabled
//                   input.setOnKeyTyped(e -> {
//                       if(input.isEditable()){
//                           changeQuestionAnswer(input, block);
//                           changeEditableFields(fields, form, block, true);
//                       }
//
//                       System.out.println(form);
//                   });
//
//                   fields.put(question.name, input);
//                   fieldGroup.join(question.name, question.text, input);
//               }
//
//
//               // Test from: https://o7planning.org/en/11185/javafx-spinner-tutorial
////            Label label = new Label("Select Level:");
////            final Spinner<Integer> spinner = new Spinner<Integer>();
////            final int initialValue = 3;
////            // Value factory.
////            SpinnerValueFactory<Integer> valueFactory = //
////                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
////            spinner.setValueFactory(valueFactory);
////            fieldGroup.join("dummy", "dummy2", spinner);
//
//               // separator, might be useful to visually make groups apparent
////            fieldGroup.separate();
//
//
//           }
//        }
    }

    private void changeQuestionAnswer(TextInputControl field, Block block) {
        block.answer(field.getId(), field.getText());
    }

    private void changeQuestionAnswer(ComboBox field, Block block) {
        block.answer(field.getId(), field.getSelectionModel().getSelectedItem().toString());
    }

    private void changeEditableFields(HashMap<String, Control> fields, Form form, ArrayList<BlockElement> elements, boolean inEditableBlock) {
//        for(Question question : block.questions){
//            boolean conditionsMet = question.isAnswerable(form);
//            if(!conditionsMet){
//                // TODO implement answer clear() function that resets values to default
//                question.answer.setValue("");
//            }
//
//            Control field = fields.get(question.name);
//            // TODO implement more field types, and also change instanceof to something else
//            if(field instanceof ComboBox){
//                ComboBox<String> comboBoxField = (ComboBox) field;
//                comboBoxField.setDisable(!conditionsMet);
//                // If the condition was not met, we should make it reset to default
//                if(!conditionsMet){
//                    comboBoxField.getSelectionModel().clearSelection();
//                }
//            } else if(field instanceof TextInputControl) {
//                TextInputControl textInputControlField = (TextInputControl) field;
////                textInputControlField.setEditable(conditionsMet);
//                textInputControlField.setDisable(!conditionsMet);
//                if(!conditionsMet){
//                    textInputControlField.clear();
//                }
//            }
//        }
    }

    private Button createSubmitButton(Form form){
        // TODO save answers to file?
        Button submitButton = new Button("Submit (see output in console)");
        submitButton.setOnAction(e -> {

//            // Debug output, shows answer to every question in console
//            System.out.println("\nSubmit called");
//            for(Question question : form.block.questions){
//                System.out.println("\t" + question.name + " => " + question.answer.evaluate(form));
//            }
        });
        return submitButton;
    }
}
