package ql.gui.view.widgets;

import jiconfont.IconFont;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyFieldWidget extends Widget {

    private final JPanel panel;
    private final JFormattedTextField field;

    public MoneyFieldWidget(QuestionModel questionModel) {
        super(questionModel);
        NumberFormat format = DecimalFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        NumberFormatter formatter = new NumberFormatter(format);
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

            public void removeUpdate(DocumentEvent e) {
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {

                Runnable format = () -> {
                    String text = textField.getText();
                    if (!text.matches("(-)?\\d*(\\.\\d{0,2})?")) {
                        textField.setText(text.substring(0, text.length() - 1));
                    }
                };

                SwingUtilities.invokeLater(format);

                if (textField.getText().matches("(-)?\\d*(\\.\\d{0,2})?")) {
                    questionModel.changeValue(new BigDecimal(textField.getText()));
                }

            }
        });

        IconFontSwing.register(FontAwesome.getIconFont());
        Icon icon = IconFontSwing.buildIcon(FontAwesome.USD, 12);
        JLabel iconLabel = new JLabel(icon);
        this.panel = new JPanel();
        this.field = textField;

        this.panel.add(iconLabel);
        this.panel.add(field);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
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
