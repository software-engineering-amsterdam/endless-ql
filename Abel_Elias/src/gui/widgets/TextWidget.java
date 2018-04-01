package gui.widgets;

import QL.classes.values.StringValue;

import javax.swing.*;

public class TextWidget implements Widget {
    private JTextField textField;
    private StringValue value;
    private boolean fixed;

    public TextWidget(StringValue value){
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
