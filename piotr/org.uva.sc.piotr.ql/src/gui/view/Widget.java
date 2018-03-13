package gui.view;

import ast.model.expressions.Expression;
import gui.model.FormQuestion;

import javax.swing.*;

public abstract class Widget extends JComponent {
    private FormQuestion formQuestion;

    public Widget(FormQuestion formQuestion) {
        this.formQuestion = formQuestion;
    }

    public FormQuestion getFormQuestion() {
        return formQuestion;
    }

    public abstract JComponent getComponent();
    public abstract Expression.DataType getSupportedDataType();
}
