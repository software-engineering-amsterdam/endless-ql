package gui.view.widgets;

import gui.model.FormQuestion;
import gui.view.Widget;

import javax.swing.*;
import java.awt.*;

// TODO: change it into a real boolean radio widget
public class BooleanRadioWidget extends Widget {

    private final JPanel radioPanel;
    private final JRadioButton yesButton;
    private final JRadioButton noButton;

    public BooleanRadioWidget(FormQuestion formQuestion) {
        super(formQuestion);

        this.yesButton = new JRadioButton("Yes");
        this.noButton = new JRadioButton("No");

        if (formQuestion.getAssignedExpression() != null) {
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

        this.yesButton.addActionListener(e -> formQuestion.changeValue(true));

        this.noButton.addActionListener(e -> formQuestion.changeValue(false));

        this.radioPanel = radioPanel;
    }

    @Override
    public JComponent getComponent() {
        return this.radioPanel;
    }

    @Override
    public void updateValue() {
        this.yesButton.setSelected(this.getFormQuestion().getValue().getBooleanValue());
        this.noButton.setSelected(!this.getFormQuestion().getValue().getBooleanValue());
    }
}
