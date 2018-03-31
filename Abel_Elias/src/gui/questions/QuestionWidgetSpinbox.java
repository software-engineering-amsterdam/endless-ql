package gui.questions;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.listeners.QuestionListener;

import javax.swing.*;

public class QuestionWidgetSpinbox extends QuestionPanel {
    private JSpinner spinBox;

    public QuestionWidgetSpinbox(String key, Question question) {
        super(key, question);
        createControlWidget(key);
    }

    @Override
    public void createControlWidget(String key) {
        spinBox = new JSpinner();
        this.add(spinBox);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void setValue(Value value) {
        this.spinBox.setValue((Integer) value.getValue());
    }

    @Override
    public void setWidgetFixed() {
        this.spinBox.setEnabled(false);
    }

    @Override
    public void setQuestionChangeListener(QuestionListener questionListener) {
        spinBox.addChangeListener(questionListener.new SpinBoxListener(super.getQuestion().getId(), spinBox));

    }

}
