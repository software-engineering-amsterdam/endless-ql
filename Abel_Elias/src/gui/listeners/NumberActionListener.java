package gui.listeners;

import QL.classes.values.NumericValue;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * BoolActionListener
 * Called when a jCheckbox control is changed
 */
public class NumberActionListener implements DocumentListener {

    private JTextField textField;
    private NumericValue value;
    private boolean modified;

    public NumberActionListener(NumericValue value, JTextField textField) {
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
        textField.requestFocus();
        if (!modified) {
            modified = true;
            SwingUtilities.invokeLater(
                    () -> {
                        boolean correctInput = true;
                        Double input = 0.0;
                        try {
                            String textString = textField.getText();
                            input = Double.parseDouble(textString);
                        } catch (NumberFormatException exception) {
                            correctInput = false;
                        }

                        if (correctInput) {
                            this.value.setValueGeneric(input);
                        }

                        textField.requestFocus();
                        modified = false;
                    }
            );
        }
    }
}