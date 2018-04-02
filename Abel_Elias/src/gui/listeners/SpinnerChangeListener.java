package gui.listeners;

import QL.classes.values.NumericValue;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * BoolActionListener
 * Called when a jCheckbox control is changed
 */
public class SpinnerChangeListener implements ChangeListener {

    private JSpinner spinner;
    private NumericValue value;

    public SpinnerChangeListener(NumericValue value, JSpinner spinner) {
        this.value = value;
        this.spinner = spinner;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        SpinnerModel spinnerModel = spinner.getModel();
        value.setValueGeneric(spinnerModel.getValue());
    }
}