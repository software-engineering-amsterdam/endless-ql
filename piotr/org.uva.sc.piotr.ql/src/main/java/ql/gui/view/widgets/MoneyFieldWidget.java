package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyFieldWidget extends Widget {

    private final JFormattedTextField field;

    public MoneyFieldWidget(QuestionModel questionModel) {
        super(questionModel);
        NumberFormat format = DecimalFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField textField = new JFormattedTextField(formatter);

        if (questionModel.getAssignedExpression() != null) {
            textField.setEditable(false);
        }

        textField.setColumns(15);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) { }

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
                    questionModel.changeValue(new BigDecimal(textField.getText()));
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
        this.field.setValue(this.getQuestionModel().getQLDataTypeValue().getValue());
    }

}
