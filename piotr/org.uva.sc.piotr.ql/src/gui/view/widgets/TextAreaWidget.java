package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextAreaWidget extends Widget {

    private JTextArea textArea;

    public TextAreaWidget(FormQuestionHolder formQuestionHolder) {

        super(formQuestionHolder);

        JTextArea area = new JTextArea();

        if (formQuestionHolder.getAssignedExpression() != null) {
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
                formQuestionHolder.changeValue(area.getText());
            }
        });

        this.textArea = area;
    }

    @Override
    public JComponent getComponent() {
        return this.textArea;
    }

    @Override
    public void updateValue() {
        this.textArea.setText(this.getFormQuestionHolder().getValueHolder().getStringValue());
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.STRING;
    }

}
