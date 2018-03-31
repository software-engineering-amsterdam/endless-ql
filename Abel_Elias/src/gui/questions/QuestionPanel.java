package gui.questions;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.listeners.QuestionListener;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.EventListener;

public abstract class QuestionPanel extends JPanel {
    private String key;
    private Question question;
    private Boolean isActive;

    public QuestionPanel(String key, Question question) {
        this.key = key;
        this.question = question;
        this.isActive = question.isVisible();
        this.add(new JLabel(question.getText()));
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
    }

    public abstract void createControlWidget(String key);

    public Question getQuestion() {
        return question;
    }

    public abstract JComponent getComponent();

    public abstract void setValue(Value value);

    public abstract void setWidgetFixed ();

    public abstract void setQuestionChangeListener(QuestionListener questionListener);

}