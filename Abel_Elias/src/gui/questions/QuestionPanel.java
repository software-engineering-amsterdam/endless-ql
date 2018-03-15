package gui.questions;

import classes.Question;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private String key;
    private Question question;

    public QuestionPanel(String key, Question question) {
        this.key = key;
        this.question = question;
        this.setLayout(new BorderLayout());
        this.add(new JLabel(question.getText()), BorderLayout.NORTH);
    }


}
