package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioWidget extends QuestionWidget {

    private JRadioButton trueButton;
    private JRadioButton falseButton;
    private ButtonGroup buttonGroup;

    public RadioWidget(Question question, Value value, boolean readOnly, Style style, String trueLabel, String falseLabel) {
        super(question);

        trueButton = new JRadioButton(trueLabel);
        falseButton = new JRadioButton(falseLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueButton);
        buttonGroup.add(falseButton);

        buttonGroup.setSelected(falseButton.getModel(), true);
        if((boolean)value.getValue()){
            buttonGroup.setSelected(trueButton.getModel(), true);
        }

        falseButton.setEnabled(readOnly);
        trueButton.setEnabled(readOnly);

        this.add(falseButton, 1);
        this.add(trueButton, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        trueButton.addItemListener(e -> questionChangeListener.onQuestionChanged(question, new BooleanValue(trueButton.isSelected())));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        falseButton.setBackground(color);
        trueButton.setBackground(color);
    }
}
