package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class BooleanDropdownWidget extends Widget {

    private final JComboBox comboBox;
    private String yesText = "Yes";
    private String noText = "No";

    public BooleanDropdownWidget(QuestionModel questionModel) {
        super(questionModel);

        Vector model = new Vector<Boolean>();
        model.addElement(Boolean.FALSE);
        model.addElement(Boolean.TRUE);

        JComboBox comboBox = new JComboBox(model);
        comboBox.setRenderer(this.getRefreshedDefaultListCellRenderer());

        if (questionModel.getAssignedExpression() != null) {
            comboBox.setEnabled(false);
        }

        comboBox.addActionListener(e -> {
            Boolean selectedItem = (Boolean) comboBox.getSelectedItem();
            questionModel.changeValue(selectedItem);
        });

        this.comboBox = comboBox;
    }

    @Override
    public JComponent getComponent() {
        return this.comboBox;
    }

    @Override
    public void updateValue() {
        Boolean value = (Boolean) this.getQuestionModel().getQLDataTypeValue().getValue();
        int index = value ? 1 : 0;
        this.comboBox.setSelectedIndex(index);
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.comboBox.getPreferredSize();
        size.width = width;
        this.comboBox.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.comboBox.setFont(new Font(font, Font.PLAIN, this.comboBox.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.comboBox.setFont(new Font(this.comboBox.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.comboBox.setBackground(Color.decode(color));
    }

    public void setYesText(String yesText) {
        this.yesText = yesText;
        this.comboBox.setRenderer(this.getRefreshedDefaultListCellRenderer());
    }

    public void setNoText(String noText) {
        this.noText = noText;
        this.comboBox.setRenderer(this.getRefreshedDefaultListCellRenderer());
    }

    private DefaultListCellRenderer getRefreshedDefaultListCellRenderer() {

        String yes = this.yesText;
        String no = this.noText;

        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (Boolean.TRUE.equals(value)) {
                    value = yes;
                } else if (Boolean.FALSE.equals(value)) {
                    value = no;
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        };
    }

}
