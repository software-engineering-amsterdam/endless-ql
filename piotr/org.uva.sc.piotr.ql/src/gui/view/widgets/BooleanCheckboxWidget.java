package gui.view.widgets;

import gui.model.FormQuestion;
import gui.view.Widget;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class BooleanCheckboxWidget extends Widget {

    private final JCheckBox checkbox;

    public BooleanCheckboxWidget(FormQuestion formQuestion) {
        super(formQuestion);
        JCheckBox checkbox = new JCheckBox();

        if (formQuestion.getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                formQuestion.changeValue(true);

            } else {
                formQuestion.changeValue(false);
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
        this.checkbox.setSelected(this.getFormQuestion().getValue().getBooleanValue());
    }
}
