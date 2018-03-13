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

        checkbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                formQuestionHolder.changeValue(true);

            } else {
                formQuestionHolder.changeValue(false);
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
