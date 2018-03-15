package gui.view;

import gui.model.FormQuestion;

import javax.swing.*;

public abstract class Widget extends JComponent {
    private final FormQuestion formQuestion;

    protected Widget(FormQuestion formQuestion) {
        this.formQuestion = formQuestion;
    }

    public FormQuestion getFormQuestion() {
        return formQuestion;
    }

    public abstract JComponent getComponent();
    public abstract void updateValue();
}
