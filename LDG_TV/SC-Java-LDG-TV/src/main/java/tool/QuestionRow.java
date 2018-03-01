package tool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionRow extends HBox implements Initializable {

    @FXML
    private Label lblQuestion;

    @FXML
    private HBox hbAnswer;

    private String question;
    private Node answer;

    public QuestionRow(String question, Node answer) {
        this.answer = answer;
        this.question = question;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lblQuestion.setText(this.question);
        this.hbAnswer.getChildren().add(this.answer);
    }
}
