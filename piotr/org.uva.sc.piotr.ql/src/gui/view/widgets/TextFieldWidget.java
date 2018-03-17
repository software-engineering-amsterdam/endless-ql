package gui.view.widgets;

import gui.model.QuestionModel;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldWidget extends Widget {

    private final JTextField textField;

    public TextFieldWidget(QuestionModel questionModel) {

        super(questionModel);

        JTextField field = new JTextField();
        field.setColumns(20);

        if (questionModel.getAssignedExpression() != null) {
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
                questionModel.changeValue(field.getText());
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
        this.textField.setText(this.getQuestionModel().getValue().getStringValue());
    }
}
