package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanWidget extends QuestionWidget {

    private JCheckBox checkBox = new JCheckBox();

    public BooleanWidget(Question question, Value value, boolean readOnly) {
        super(question);

        checkBox.setSelected(Boolean.valueOf(value.toString()));
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkBox.setEnabled(readOnly);


        this.add(checkBox, 1);
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
}
