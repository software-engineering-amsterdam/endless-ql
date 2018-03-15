package gui.view.widgets;

import gui.model.QuestionModel;
import gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextAreaWidget extends Widget {

    private final JTextArea textArea;

    public TextAreaWidget(QuestionModel questionModel) {

        super(questionModel);

        JTextArea area = new JTextArea();

        if (questionModel.getAssignedExpression() != null) {
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
                questionModel.changeValue(area.getText());
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
        this.textArea.setText(this.getQuestionModel().getValue().getStringValue());
    }

}
