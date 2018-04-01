package gui.widgets;

import QL.classes.values.StringValue;
import gui.listeners.DropDownListener;

import javax.swing.*;

public class DropDownWidget implements Widget {
    private JComboBox comboBox;
    private StringValue value;

    public DropDownWidget(StringValue value, Object[] options){
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
