package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.math.BigInteger;
import java.text.NumberFormat;

public class IntegerFieldWidget extends Widget {

    private final JFormattedTextField field;

    public IntegerFieldWidget(QuestionModel questionModel) {

        super(questionModel);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter formatter = new NumberFormatter(format);
        // If you want the value to be committed on each keystroke instead of focus lost
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
                    if (!text.matches("(-)?\\d*")) {
                        textField.setText(text.substring(0, text.length() - 1));
                    }
                };

                SwingUtilities.invokeLater(format);

                if (textField.getText().matches("(-)?\\d*")) {
                    questionModel.changeValue(new BigInteger(textField.getText()));
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
        this.field.setValue(this.getQuestionModel().getValue().getIntegerValue());
    }
}
