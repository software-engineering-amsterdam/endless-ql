import entity.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Form;
import model.Question;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.InputStream;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        // Parse form from specified file
        String fileName = "example.ql";
        InputStream stream = getClass().getResourceAsStream(fileName);
        Form form = FormParser.parseForm(stream);

        // https://github.com/YoriChan/FormFX

        // create a FieldGroup
//        FieldGroup fieldGroup = new FieldGroup() {{
//            join("#username", "Username:", Input.textField("Please input your username"));
//            join("#pwd", "Password:", Input.passwordField("Please input your password"));
//            join("#gender", "Gender:", Input.radioButtonBox(
//                    new OptionList() {{
//                        add("boy", true);
//                        add("girl");
//                    }}));
//            join("#bir", "Birthday:", Input.datePicker("Please select your birthday"));
//            join("#hobby", "Hobby:", Input.checkBox(
//                    new OptionList() {{
//                        add("basketball", true);
//                        add("soccer");
//                        add("program");
//                    }}));
//            join("#job", "Job:", Input.comboBox(
//                    new OptionList() {{
//                        add("programmer");
//                        add("student", true);
//                        add("teacher");
//                    }}));
//            // I change the preWidth property of the TextArea
//            join("#about", "About:", Input.textArea(250, -1, "Write something about you"));
//        }};

        // Create a group of fields composed of our form questions
        FieldGroup fieldGroup = new FieldGroup();
        for(Question question : form.block.questions){
            // TODO, change type of question based on question.answer
            fieldGroup.join("#" + question.name, question.name, Input.textField(question.text));
        }

        // Create our form grid
        GridForm gridForm = new GridForm(fieldGroup);
        gridForm.setPadding(new Insets(10, 10, 10, 10));
        gridForm.setFieldOrientation(Orientation.HORIZONTAL);
        gridForm.setAlignment(Pos.CENTER);

        // Build submit button and action listener
        // TODO change to filling out the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            User user = new User();
            gridForm.fill(user);
            System.out.println(user);
        });


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

    public static void main(String[] args) {
        launch(args);
    }
}
