package gui.view;

import gui.model.FormQuestionHolder;

import javax.swing.*;

public abstract class Widget extends JComponent {
    private final FormQuestionHolder formQuestionHolder;

    protected Widget(FormQuestionHolder formQuestionHolder) {
        this.formQuestionHolder = formQuestionHolder;
    }

    public FormQuestionHolder getFormQuestionHolder() {
        return formQuestionHolder;
    }

    public abstract JComponent getComponent();
    public abstract void updateValue();
}
