package gui.listeners;

import QL.classes.values.StringValue;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * BoolActionListener
 * Called when a jCheckbox control is changed
 */
public class TextActionListener implements DocumentListener {

    private JTextField textField;
    private StringValue value;
    private boolean modified;

    public TextActionListener(StringValue value, JTextField textField) {
        this.modified = false;
        this.value = value;
        this.textField = textField;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        actionCalled();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        actionCalled();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        actionCalled();
    }

    private void actionCalled() {
        System.out.println("txt");
        textField.requestFocus();
        if (!modified) {
            modified = true;
            SwingUtilities.invokeLater(() -> {
                value.setValue(textField.getText());

                textField.requestFocus();
                modified = false;
            });
        }
    }
}