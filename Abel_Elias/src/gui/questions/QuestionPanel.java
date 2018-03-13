package gui.questions;

import classes.statements.Question;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class QuestionPanel extends JPanel {
    private String key;
    private Question question;

    public QuestionPanel(String key, Question question) {
        this.key = key;
        this.question = question;
        this.add(new JLabel(question.getText()));
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public JPanel getQuestionPanel() {
        return this;
    }

    public void addWidgetToPanel(JComponent component) {
        this.add(component);
    }
}
