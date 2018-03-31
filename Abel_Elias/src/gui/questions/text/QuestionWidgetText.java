package gui.questions.text;

import QL.classes.Question;
import QL.classes.values.UndefinedValue;
import QL.classes.values.Value;
import gui.questions.QuestionPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionListener;
import java.util.EventListener;

public abstract class QuestionWidgetText extends QuestionPanel {

    private JTextField textField;

    public QuestionWidgetText(String key, Question question) {
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
    public void setValue(Value value) {
            this.textField.setText(value.getValue().toString());
    }

    @Override
    public void setWidgetFixed() {
        this.textField.setEditable(false);
    }
}
