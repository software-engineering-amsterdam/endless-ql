package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class SliderWidget extends QuestionWidget implements ChangeListener{

    private JSlider slider;
    private QuestionChangeListener questionChangeListener;

    public SliderWidget(Question question, Value value, boolean readOnly, Style style, int start, int end, int steps) {
        super(question);

        int currentValue = (int)value.getValue();
        if (currentValue > end) {
            currentValue = end;
        } else if (currentValue < start) {
            currentValue = start;
        }

        slider = new JSlider(start, end, currentValue);
        slider.setMinorTickSpacing(steps);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setEnabled(readOnly);
        slider.setLabelTable(slider.createStandardLabels(steps));

        this.add(slider, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        this.questionChangeListener = questionChangeListener;
        slider.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(!slider.getValueIsAdjusting()){
            questionChangeListener.onQuestionChanged(question, new IntegerValue(slider.getValue()));
        }
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        slider.setBackground(color);
    }
}
