package gui.listeners;

import QL.classes.values.BooleanValue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BoolActionListener
 * Called when a jCheckbox control is changed
 */
public class BoolActionListener implements ActionListener {

    private JCheckBox checkBox;
    private BooleanValue value;

    public BoolActionListener(BooleanValue value, JCheckBox checkBox) {
        this.value = value;
        this.checkBox = checkBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected()) {
            value.setValue(true);
        } else {
            value.setValue(false);
        }

        checkBox.requestFocus();
    }
}