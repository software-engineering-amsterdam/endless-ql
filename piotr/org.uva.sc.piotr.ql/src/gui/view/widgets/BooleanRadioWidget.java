package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.view.FormPanel;
import gui.view.Widget;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanRadioWidget extends Widget {

    private JCheckBox checkbox;

    public BooleanRadioWidget(FormPanel formPanel) {
        super(formPanel);

        JCheckBox checkbox = new JCheckBox();

        if (formPanel.getFormQuestion().getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox SELECTED");
                } else {
                    System.out.println("Checkbox DESELECTED");
                }

            }
        });

        this.checkbox = checkbox;
    }

    @Override
    public JComponent getComponent() {
        return this.checkbox;
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.BOOLEAN;
    }
}
