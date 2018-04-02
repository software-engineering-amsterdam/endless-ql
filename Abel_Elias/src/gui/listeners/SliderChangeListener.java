package gui.listeners;

import QL.classes.values.NumericValue;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * BoolActionListener
 * Called when a jCheckbox control is changed
 */
public class SliderChangeListener implements ChangeListener {
    private JSlider slider;
    private JLabel label;
    private NumericValue value;

    public SliderChangeListener(NumericValue value, JSlider slider, JLabel label) {
        this.value = value;
        this.slider = slider;
        this.label = label;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!slider.getValueIsAdjusting()) {
            value.setValueGeneric(slider.getValue());
            label.setText("" + slider.getValue());
        }
    }
}