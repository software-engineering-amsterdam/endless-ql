package gui.view.widgets;

import gui.model.FormQuestion;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class IntegerFieldWidget extends Widget {

    private final JFormattedTextField field;

    public IntegerFieldWidget(FormQuestion formQuestion) {

        super(formQuestion);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter formatter = new NumberFormatter(format);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField textField = new JFormattedTextField(formatter);

        if (formQuestion.getAssignedExpression() != null) {
            textField.setEditable(false);
        }

        textField.setColumns(15);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {
                String clearText = textField.getText().replaceAll("[^0-9]", "");
                String safeText = clearText.equals("") ? "0" : clearText;
                formQuestion.changeValue(Integer.parseInt(safeText));
            }
        });

        this.field = textField;
    }

    @Override
    public JComponent getComponent() {
        return this.field;
    }

    @Override
    public void updateValue() {
        this.field.setValue(this.getFormQuestion().getValue().getIntegerValue());
    }
}
