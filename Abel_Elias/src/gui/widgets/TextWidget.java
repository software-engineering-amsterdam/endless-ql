package gui.widgets;

import QL.classes.values.NumericValue;
import QL.classes.values.StringValue;
import QL.classes.values.Value;
import gui.listeners.NumberActionListener;
import gui.listeners.TextActionListener;

import javax.swing.*;

public class TextWidget implements Widget {
    private JTextField textField;
    private Value value;

    public TextWidget(StringValue value) {
        this.value = value;

        textField = new JTextField("", 20);
        textField.getDocument().addDocumentListener(new TextActionListener(value, textField));

        refresh();
    }

    public TextWidget(NumericValue value) {
        this.value = value;

        textField = new JTextField("", 20);
        textField.getDocument().addDocumentListener(new NumberActionListener(value, textField));

        refresh();
    }

    @Override
    public JTextField getJComponent() {
        return textField;
    }

    @Override
    public void refresh() {
        textField.setText(value.getValue().toString());
    }
}
