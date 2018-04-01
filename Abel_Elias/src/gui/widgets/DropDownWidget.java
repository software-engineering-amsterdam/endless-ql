package gui.widgets;

import QL.classes.values.StringValue;
import QL.classes.values.Value;
import gui.listeners.DropDownListener;

import javax.swing.*;

public class DropDownWidget implements Widget {
    private JComboBox comboBox;
    private Value value;

    public DropDownWidget(Value value, Object[] options){
        this.value = value;

        comboBox = new JComboBox(options);
        comboBox.addActionListener(new DropDownListener(value, comboBox));

        refresh();
    }

    @Override
    public JComboBox getJComponent() {
        return comboBox;
    }

    @Override
    public void refresh() {
        comboBox.setSelectedItem(value.getValue());
    }
}
