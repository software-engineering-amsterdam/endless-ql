package gui.questions;

import classes.Question;
import gui.FormBuilder;

import javax.swing.*;
import java.awt.*;

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
}
