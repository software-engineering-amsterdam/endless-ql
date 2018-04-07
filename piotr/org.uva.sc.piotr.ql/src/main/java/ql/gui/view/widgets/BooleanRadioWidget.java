package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.awt.*;

public class BooleanRadioWidget extends Widget {

    private final JPanel radioPanel;
    private final JRadioButton yesButton;
    private final JRadioButton noButton;

    public BooleanRadioWidget(QuestionModel questionModel) {
        super(questionModel);

        this.yesButton = new JRadioButton("Yes");
        this.noButton = new JRadioButton("No");

        if (questionModel.getAssignedExpression() != null) {
            this.yesButton.setEnabled(false);
            this.noButton.setEnabled(false);
        }

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.yesButton);
        buttonGroup.add(this.noButton);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 2));
        radioPanel.add(this.yesButton);
        radioPanel.add(this.noButton);

        this.yesButton.addActionListener(e -> questionModel.changeValue(true));

        this.noButton.addActionListener(e -> questionModel.changeValue(false));

        this.radioPanel = radioPanel;
    }

    @Override
    public JComponent getComponent() {
        return this.radioPanel;
    }

    @Override
    public void updateValue() {
        this.yesButton.setSelected((Boolean) this.getQuestionModel().getQLDataTypeValue().getValue());
        this.noButton.setSelected(!(Boolean) this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.radioPanel.getPreferredSize();
        size.width = width;
        this.radioPanel.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.yesButton.setFont(new Font(font, Font.PLAIN, this.yesButton.getFont().getSize()));
        this.noButton.setFont(new Font(font, Font.PLAIN, this.noButton.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.yesButton.setFont(new Font(this.yesButton.getFont().getName(), Font.PLAIN, fontSize));
        this.noButton.setFont(new Font(this.noButton.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.radioPanel.setBackground(Color.decode(color));
    }

    public void setYesText(String yesText) {
        this.yesButton.setText(yesText);
    }

    public void setNoText(String noText) {
        this.noButton.setText(noText);
    }

}
