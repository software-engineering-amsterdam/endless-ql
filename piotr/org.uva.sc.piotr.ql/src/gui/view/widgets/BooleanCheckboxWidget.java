package gui.view.widgets;

import gui.model.QuestionModel;
import gui.view.Widget;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class BooleanCheckboxWidget extends Widget {

    private final JCheckBox checkbox;

    public BooleanCheckboxWidget(QuestionModel questionModel) {
        super(questionModel);
        JCheckBox checkbox = new JCheckBox();

        if (questionModel.getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                questionModel.changeValue(true);

            } else {
                questionModel.changeValue(false);
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
        this.checkbox.setSelected(this.getQuestionModel().getValue().getBooleanValue());
    }
}
