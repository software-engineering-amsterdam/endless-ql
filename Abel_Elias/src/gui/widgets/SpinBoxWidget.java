package gui.widgets;

import QL.classes.values.NumericValue;
import gui.listeners.SpinnerChangeListener;

import javax.swing.*;

public class SpinBoxWidget implements Widget {
    private JSpinner spinner;
    private NumericValue value;

    public SpinBoxWidget(NumericValue value) {
        this.value = value;

        spinner = new JSpinner();
        spinner.addChangeListener(new SpinnerChangeListener(value, spinner));

        refresh();
    }

    @Override
    public JSpinner getJComponent() {
        return spinner;
    }

    @Override
    public void refresh() {
        spinner.setValue((int) value.getComputationValue());
    }
}
