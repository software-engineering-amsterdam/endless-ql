package gui.listeners;

import QL.classes.values.Value;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropDownListener implements ActionListener {
    private Value value;
    private JComboBox comboBox;

    public DropDownListener(Value value, JComboBox comboBox) {
        this.value = value;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ComboBoxModel cbModel = comboBox.getModel();
        value.setValueGeneric(cbModel.getSelectedItem());
    }
}
