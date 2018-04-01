package gui.widgets;

import QL.classes.values.BooleanValue;
import gui.listeners.BoolActionListener;

import javax.swing.*;

public class CheckBoxWidget implements Widget {
    private JCheckBox checkBox;
    private BooleanValue value;

    public CheckBoxWidget(BooleanValue value){
        this.value = value;

        checkBox = new JCheckBox();
        checkBox.addActionListener(new BoolActionListener(value, checkBox));

        refresh();
    }

    @Override
    public JCheckBox getJComponent() {
        return checkBox;
    }

    @Override
    public void refresh() {
        checkBox.setSelected(value.getValue());
    }
}
