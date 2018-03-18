package gui.questions;

import classes.Question;
import classes.values.UndefinedValue;
import classes.values.Value;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionListener;
import java.util.EventListener;

public abstract class QuestionPanelText extends QuestionPanel {

    private JTextField textField;

    public QuestionPanelText(String key, Question question) {
        super(key, question);
        createControlWidget(key);
    }


    @Override
    public void createControlWidget(String key) {
        textField = new JTextField("", 20);
        this.add(textField);
    }

    @Override
    public JComponent getComponent() {
        return this.textField;
    }

    @Override
    public abstract void setListener(EventListener listener);

    @Override
    public void setValue(Value value) {
            this.textField.setText(value.getValue().toString());
    }
}
