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

public class BooleanWidget extends QuestionWidget {

    private JCheckBox checkBox;

    public BooleanWidget(Question question, Value value, boolean readOnly, Style style) {
        super(question);

        this.checkBox = new JCheckBox();
        this.checkBox.setSelected(Boolean.valueOf(value.toString()));
        this.checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        this.checkBox.setEnabled(readOnly);
        this.add(checkBox, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                questionChangeListener.onQuestionChanged(question, new BooleanValue(checkBox.isSelected()));
            }
        });
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        checkBox.setBackground(color);
    }
}
