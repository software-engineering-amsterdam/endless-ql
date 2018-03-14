package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyFieldWidget extends Widget {

    private final JFormattedTextField field;

    public MoneyFieldWidget(FormQuestionHolder formQuestionHolder) {
        super(formQuestionHolder);
        NumberFormat format = DecimalFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField textField = new JFormattedTextField(formatter);

        if (formQuestionHolder.getAssignedExpression() != null) {
            textField.setEditable(false);
        }

        textField.setColumns(15);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                //warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {

                Runnable format = () -> {
                    String text = textField.getText();
                    if (!text.matches("(-)?\\d*(\\.\\d{0,2})?")) {
                        textField.setText(text.substring(0, text.length() - 1));
                    }
                };

                SwingUtilities.invokeLater(format);

                if (textField.getText().matches("(-)?\\d*(\\.\\d{0,2})?")) {
                    formQuestionHolder.changeValue(new BigDecimal(textField.getText()));
                }

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
        this.field.setValue(this.getFormQuestionHolder().getValueHolder().getDecimalValue());
    }

}
