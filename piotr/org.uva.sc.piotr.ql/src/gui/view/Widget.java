package gui.view;

import gui.model.QuestionModel;

import javax.swing.*;

public abstract class Widget extends JComponent {
    private final QuestionModel questionModel;

    protected Widget(QuestionModel questionModel) {
        this.questionModel = questionModel;
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public abstract JComponent getComponent();
    public abstract void updateValue();
}
