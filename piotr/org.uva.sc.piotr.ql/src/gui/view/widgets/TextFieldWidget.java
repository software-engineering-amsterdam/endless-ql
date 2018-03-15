package gui.view.widgets;

import gui.model.FormQuestion;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldWidget extends Widget {

    private final JTextField textField;

    public TextFieldWidget(FormQuestion formQuestion) {

        super(formQuestion);

        JTextField field = new JTextField();
        field.setColumns(20);

        if (formQuestion.getAssignedExpression() != null) {
            field.setEditable(false);
        }

        field.getDocument().addDocumentListener(new DocumentListener() {

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
                formQuestion.changeValue(field.getText());
            }
        });
        this.textField = field;
    }

    @Override
    public JComponent getComponent() {
        return this.textField;
    }

    @Override
    public void updateValue() {
        this.textField.setText(this.getFormQuestion().getValue().getStringValue());
    }
}
