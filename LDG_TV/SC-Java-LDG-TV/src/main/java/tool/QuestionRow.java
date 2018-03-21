package tool;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
public class QuestionRow extends Row {

    public QuestionRow(String question, Node answer, boolean hide) {
        super(question, answer, hide);
    }
}
