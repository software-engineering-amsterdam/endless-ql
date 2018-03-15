package org.uva.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.gui.QuestionChangeListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanWidget extends QuestionWidget {

    private JCheckBox checkBox = new JCheckBox();

    public BooleanWidget(Question question, Value value, boolean readOnly, QuestionChangeListener questionChangeListener) {
        super(question);

        checkBox.setSelected(Boolean.valueOf(value.toString()));
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkBox.setEnabled(readOnly);

        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                questionChangeListener.onQuestionChanged(question.getName(), new BooleanValue(checkBox.isSelected()));
            }
        });


        this.add(checkBox, 1);
    }
}
