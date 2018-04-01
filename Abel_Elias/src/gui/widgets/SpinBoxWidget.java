package gui.widgets;

import QL.classes.values.BooleanValue;
import QL.classes.values.NumericValue;
import QL.classes.values.Value;
import gui.listeners.BoolActionListener;
import gui.listeners.SpinnerChangeListener;

import javax.swing.*;
import java.util.List;

public class SpinBoxWidget implements Widget {
    private JSpinner spinner;
    private NumericValue value;

    public SpinBoxWidget(NumericValue value){
        this.value = value;

        spinner = new JSpinner();
        spinner.addChangeListener(new SpinnerChangeListener(value,spinner));

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
