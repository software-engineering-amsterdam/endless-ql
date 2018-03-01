package tool.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
public class QuestionRow extends HBox {

    @FXML
    private Label lblQuestion;

    @FXML
    private HBox hbAnswer;

    public QuestionRow(String question, Node answer) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionRow.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();

            this.lblQuestion.setText(question);
            this.hbAnswer.getChildren().add(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
