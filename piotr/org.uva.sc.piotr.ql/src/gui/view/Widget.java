package gui.view;

import ast.model.expressions.Expression;

import javax.swing.*;

public abstract class Widget extends JComponent {
    private FormPanel formPanel;

    public Widget(FormPanel formPanel) {
        this.formPanel = formPanel;
    }

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public abstract JComponent getComponent();
    public abstract Expression.DataType getSupportedDataType();
}
