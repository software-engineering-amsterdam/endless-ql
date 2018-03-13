package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class IntegerFieldWidget extends Widget {

    private JFormattedTextField field;

    public IntegerFieldWidget(FormQuestionHolder formQuestionHolder) {

        super(formQuestionHolder);

        NumberFormat format = NumberFormat.getIntegerInstance();
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
                String clearText = textField.getText().replaceAll("[^0-9]", "");
                String safeText = clearText.equals("") ? "0" : clearText;
                System.out.println("Integer changed to: " + safeText);
                formQuestionHolder.changeValue(Integer.parseInt(safeText));
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
