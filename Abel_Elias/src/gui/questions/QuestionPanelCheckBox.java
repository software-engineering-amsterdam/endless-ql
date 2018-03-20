package gui.questions;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.FormBuilder;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class QuestionPanelCheckBox extends QuestionPanel {

    private JCheckBox checkBox;

    public QuestionPanelCheckBox(String key, Question question) {
        super(key, question);
        createControlWidget(key);
    }

    @Override
    public void createControlWidget(String key) {
        checkBox = new JCheckBox();
        this.add(checkBox);
    }

    @Override
    public JComponent getComponent() {
        return this.checkBox;
    }

    @Override
    public void setListener(EventListener listener) {
        FormBuilder.BoolActionListener boolActionListener = (FormBuilder.BoolActionListener) listener;
        checkBox.addActionListener(boolActionListener);
    }

    @Override
    public void setWidgetFixed() {
        this.checkBox.setEnabled(false);
    }

    @Override
    public void setValue(Value value) {
        this.checkBox.setSelected((boolean) value.getValue());
    }
}
