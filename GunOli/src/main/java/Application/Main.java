package Application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


import ParseObjects.Question;
import ParseObjects.Condition;
import ParseObjects.Form;


public class Main extends Application{

    @Override
    public void start(Stage stage){
       Form form = buildQLForm();

       if(form == null) {
           Platform.exit();
       }
    }


    public Form buildQLForm(){
        String file = "example.ql";
        
        Parser Parser = new Parser();
        Form form = Parser.parseInputToForm(file);


        //Debug form
        for(Question question : form.getBlock().getQuestions()){
            System.out.println(question.getIdentifier()+ " : " + question.getText()+" : "+ question.getType());
        }

        for(Condition condition : form.getBlock().getConditions()){
            for(Question question : condition.getBlock().getQuestions()){
                System.out.println(question.getIdentifier()+ " : " + question.getText()+" : "+ question.getType());
            }
        }

        return form;
    }

    public static void main(String[] args) {
        launch(args);
    }
}