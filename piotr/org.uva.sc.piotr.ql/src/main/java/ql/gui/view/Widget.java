package ql.gui.view;

import ql.gui.model.QuestionModel;

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

    public abstract void setWidth(Integer width);

    public abstract void setFont(String font);

    public abstract void setFontSize(Integer fontSize);

    public abstract void setColor(String color);
}
