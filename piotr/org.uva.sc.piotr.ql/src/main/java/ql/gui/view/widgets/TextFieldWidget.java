package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

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
        this.textField.setText((String) this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.textField.getPreferredSize();
        size.width = width;
        this.textField.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.textField.setFont(new Font(font, Font.PLAIN, this.textField.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.textField.setFont(new Font(this.textField.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.textField.setBackground(Color.decode(color));
    }
}
