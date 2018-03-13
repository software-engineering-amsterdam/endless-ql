package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.view.FormPanel;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalFieldWidget extends Widget {

    private JFormattedTextField field;

    public DecimalFieldWidget(FormPanel formPanel) {
        super(formPanel);
        NumberFormat format = DecimalFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField textField = new JFormattedTextField(formatter);

        if (formPanel.getFormQuestion().getAssignedExpression() != null) {
            textField.setEditable(false);
        }

        textField.setColumns(15);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changed update");
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                System.out.println("remove update");
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                System.out.println("insert update");
                warn();
            }

            private void warn() {
                String clearText = textField.getText().replaceAll("[^0-9.]", "");
                String safeText = clearText.equals("") ? "0" : clearText;
                System.out.println("Decimal changed to: " + safeText);
            }
        });

        this.field = textField;
    }

    @Override
    public JComponent getComponent() {
        return this.field;
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.DECIMAL;
    }
}
