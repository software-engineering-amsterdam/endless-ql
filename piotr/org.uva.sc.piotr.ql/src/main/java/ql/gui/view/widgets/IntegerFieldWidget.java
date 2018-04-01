package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
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
        this.field.setValue(this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.field.getPreferredSize();
        size.width = width;
        this.field.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.field.setFont(new Font(font, Font.PLAIN, this.field.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.field.setFont(new Font(this.field.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.field.setBackground(Color.decode(color));
    }
}
