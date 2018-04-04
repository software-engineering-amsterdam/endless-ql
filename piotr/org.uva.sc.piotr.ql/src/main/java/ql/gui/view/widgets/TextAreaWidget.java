package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

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
        this.textArea.setText((String) this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.textArea.getPreferredSize();
        size.width = width;
        this.textArea.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.textArea.setFont(new Font(font, Font.PLAIN, this.textArea.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.textArea.setFont(new Font(this.textArea.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.textArea.setBackground(Color.decode(color));
    }
}
