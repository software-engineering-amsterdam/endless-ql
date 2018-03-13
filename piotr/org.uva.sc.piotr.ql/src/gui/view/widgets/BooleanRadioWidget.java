package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestion;
import gui.view.FormPanel;
import gui.view.Widget;
import logic.evaluators.UniversalTypeValue;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanRadioWidget extends Widget {

    private JCheckBox checkbox;

    public BooleanRadioWidget(FormQuestion formQuestion) {
        super(formQuestion);

        JCheckBox checkbox = new JCheckBox();

        if (formQuestion.getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Checkbox SELECTED");
                    formQuestion.getValue().setBooleanValue(true);
                } else {
                    System.out.println("Checkbox DESELECTED");
                    formQuestion.getValue().setBooleanValue(false);
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
