package gui.questions;

import classes.Question;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.util.EventListener;

public abstract class QuestionPanelText extends QuestionPanel{

    private JTextField textField;

    public QuestionPanelText(String key, Question question) {
        super(key, question);
    }


    @Override
    public void createControlWidget(String key) {
        textField = new JTextField();
        this.add(textField);
    }

    @Override
    public JComponent getComponent() {
        return this.textField;
    }
    @Override
    public abstract void setListener(EventListener listener);
}
