package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.QuestionChangeListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanWidget extends QuestionWidget {

    public BooleanWidget(Question question, Value value, QuestionChangeListener questionChangeListener) {
        super(question);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(Boolean.valueOf(value.toString()));
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);

        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                questionChangeListener.onQuestionChanged(question.getName(), new BooleanValue(checkBox.isSelected()));
            }
        });

        this.add(checkBox, 1);
    }
}
