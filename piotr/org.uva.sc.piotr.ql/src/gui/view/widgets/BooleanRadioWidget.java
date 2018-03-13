package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanRadioWidget extends Widget {

    private JCheckBox checkbox;

    public BooleanRadioWidget(FormQuestionHolder formQuestionHolder) {
        super(formQuestionHolder);

        JCheckBox checkbox = new JCheckBox();

        if (formQuestionHolder.getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
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
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.BOOLEAN;
    }
}
