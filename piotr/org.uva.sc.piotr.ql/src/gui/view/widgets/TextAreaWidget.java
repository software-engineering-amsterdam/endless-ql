package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.view.FormPanel;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextAreaWidget extends Widget {

    private JTextArea textArea;

    public TextAreaWidget(FormPanel formPanel) {

        super(formPanel);

        JTextArea area = new JTextArea();

        if (formPanel.getFormQuestion().getAssignedExpression() != null) {
            area.setEditable(false);
        }

        area.setColumns(20);

        area.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                this.pushEvent();
            }

            public void removeUpdate(DocumentEvent e) {
                this.pushEvent();
            }

            public void insertUpdate(DocumentEvent e) {
                this.pushEvent();
            }

            private void pushEvent() {

                System.out.println("TextFieldWidget new value: " + area.getText());
//                formQuestion.getValue().setStringValue(textField.getText());
//                anchor.evaluate();
//                textField.setVisible(formQuestion.getVisibility().getBooleanValue());
            }
        });

        this.textArea = area;
    }

    @Override
    public JComponent getComponent() {
        return this.textArea;
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.STRING;
    }

}
