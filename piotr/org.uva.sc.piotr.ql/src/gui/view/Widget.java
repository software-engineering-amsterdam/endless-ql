package gui.view;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;

import javax.swing.*;

public abstract class Widget extends JComponent {
    private FormQuestionHolder formQuestionHolder;

    public Widget(FormQuestionHolder formQuestionHolder) {
        this.formQuestionHolder = formQuestionHolder;
    }

    public FormQuestionHolder getFormQuestionHolder() {
        return formQuestionHolder;
    }

    public abstract JComponent getComponent();
    public abstract Expression.DataType getSupportedDataType();
    public abstract void updateValue();
}
