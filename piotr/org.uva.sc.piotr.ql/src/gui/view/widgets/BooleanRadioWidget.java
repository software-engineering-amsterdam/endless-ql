package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;
import java.awt.*;

// TODO: change it into a real boolean radio widget
public class BooleanRadioWidget extends Widget {

    private JPanel radioPanel;
    private JRadioButton yesButton;
    private JRadioButton noButton;

    public BooleanRadioWidget(FormQuestionHolder formQuestionHolder) {
        super(formQuestionHolder);

        this.yesButton = new JRadioButton("Yes");
        this.noButton = new JRadioButton("No");

        if (formQuestionHolder.getAssignedExpression() != null) {
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

        this.yesButton.addActionListener(e -> {
            formQuestionHolder.changeValue(true);
        });

        this.noButton.addActionListener(e -> {
            formQuestionHolder.changeValue(false);
        });

        this.radioPanel = radioPanel;
    }

    @Override
    public JComponent getComponent() {
        return this.radioPanel;
    }

    @Override
    public void updateValue() {
        this.yesButton.setSelected(this.getFormQuestionHolder().getValueHolder().getBooleanValue());
        this.noButton.setSelected(!this.getFormQuestionHolder().getValueHolder().getBooleanValue());
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.BOOLEAN;
    }
}
