package gui.view.widgets;

import gui.model.FormQuestion;
import gui.view.Widget;

import javax.swing.*;

public class IntegerSpinnerWidget extends Widget {

    private final JSpinner spinner;

    public IntegerSpinnerWidget(FormQuestion formQuestion) {
        super(formQuestion);

        SpinnerModel spinnerModel = new SpinnerNumberModel(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1
        );

        JSpinner spinner = new JSpinner(spinnerModel);

        if (formQuestion.getAssignedExpression() != null) {
            spinner.setEnabled(false);
        }

        spinner.addChangeListener(e -> formQuestion.changeValue((Integer) spinner.getValue()));

        this.spinner = spinner;
    }

    @Override
    public void updateValue() {
        this.spinner.setValue(this.getFormQuestion().getValue().getIntegerValue());
    }

    @Override
    public JComponent getComponent() {
        return this.spinner;
    }
}
