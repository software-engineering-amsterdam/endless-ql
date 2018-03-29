package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;

public class DropdownWidget extends QuestionWidget {

    private JComboBox<String> comboBox;

    public DropdownWidget(Question question, Value value, boolean readOnly, Style style, String trueLabel, String falseLabel) {
        super(question);

        comboBox = new JComboBox<>();
        comboBox.addItem(trueLabel);
        comboBox.addItem(falseLabel);

        comboBox.setSelectedItem(falseLabel);
        if ((boolean) value.getValue()) {
            comboBox.setSelectedItem(trueLabel);
        }

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }

        comboBox.setEnabled(readOnly);

        this.add(comboBox, 1);
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        comboBox.addActionListener(e -> questionChangeListener.onQuestionChanged(question, new BooleanValue(comboBox.getSelectedIndex() == 0)));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        comboBox.setBackground(color);
    }
}
