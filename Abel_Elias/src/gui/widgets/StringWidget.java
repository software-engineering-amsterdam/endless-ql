package gui.widgets;

import QL.classes.values.BooleanValue;
import QL.classes.values.StringValue;

import javax.swing.*;

public class StringWidget implements Widget {
    private JTextField textField;
    private StringValue value;
    private boolean fixed;

    StringWidget(StringValue value, boolean fixed){
        this.value = value;
        textField = new JTextField("", 20);
        refresh();
    }

    @Override
    public JTextField getJComponent() {
        return textField;
    }

    @Override
    public void refresh() {
        textField.setText(value.getValue());
    }
}
