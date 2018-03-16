package gui.view.widgets;

import gui.model.QuestionModel;
import gui.view.Widget;

import javax.swing.*;

public class IntegerSpinnerWidget extends Widget {

    private final JSpinner spinner;

    public IntegerSpinnerWidget(QuestionModel questionModel) {
        super(questionModel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1
        );

        JSpinner spinner = new JSpinner(spinnerModel);

        if (questionModel.getAssignedExpression() != null) {
            spinner.setEnabled(false);
        }

        spinner.addChangeListener(e -> questionModel.changeValue((Integer) spinner.getValue()));

        this.spinner = spinner;
    }

    @Override
    public void updateValue() {
        this.spinner.setValue(this.getQuestionModel().getValue().getIntegerValue());
    }

    @Override
    public JComponent getComponent() {
        return this.spinner;
    }
}
