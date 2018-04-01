package gui.questions;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.GUIBuilder;
import gui.listeners.QuestionValueListener;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class QuestionWidgetCheckBox extends QuestionPanel {

    private JCheckBox checkBox;


    public QuestionWidgetCheckBox(String key, Question question) {
        super(key, question);
        createControlWidget(key);
    }

    @Override
    public void createControlWidget(String key) {
        checkBox = new JCheckBox();
//        Question question = super.getQuestion();
//        this.setValue(question.getValue());
        this.add(checkBox);
    }

    @Override
    public JComponent getComponent() {
        return this.checkBox;
    }

    @Override
    public void setWidgetFixed() {
        this.checkBox.setEnabled(false);
    }

    @Override
    public void setQuestionChangeListener(QuestionValueListener questionValueListener) {
        checkBox.addActionListener(questionValueListener.new BoolActionListener(super.getQuestion().getId(), checkBox));
    }

    @Override
    public void setValue(Value value) {
        this.checkBox.setSelected((boolean) value.getValue());
    }
}
