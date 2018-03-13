package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanCheckboxWidget extends Widget {

    private JCheckBox checkbox;

    public BooleanCheckboxWidget(FormQuestionHolder formQuestionHolder) {
        super(formQuestionHolder);
        JCheckBox checkbox = new JCheckBox();

        if (formQuestionHolder.getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                // inform the FormStateManager that there is a new value for this particular widget

                // he re-evaluates the form and updates values and visibility for all widgets

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox SELECTED");
                    formQuestionHolder.changeValue(true);

                } else {
                    System.out.println("Checkbox DESELECTED");
                    formQuestionHolder.changeValue(false);
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
    public void updateValue() {
        this.checkbox.setSelected(this.getFormQuestionHolder().getValueHolder().getBooleanValue());
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.BOOLEAN;
    }
}
