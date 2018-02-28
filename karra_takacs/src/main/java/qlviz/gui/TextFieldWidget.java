package qlviz.gui;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class TextFieldWidget implements Widget{
	


public void addFields(List<String> questionsInBlock,VBox formFields) {
HBox hbox = new HBox();
for(String questionText:questionsInBlock) {
Label label = new Label(questionText);
TextField textField = new TextField(); 
textField.setId(questionText);
textField.setMinWidth(50);
textField.setPrefWidth(50);
hbox.getChildren().add(label);
hbox.getChildren().add(textField);
formFields.getChildren().add(hbox);
}
}

}
