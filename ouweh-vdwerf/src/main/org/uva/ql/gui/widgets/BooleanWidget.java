package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;

import javax.swing.*;

public class BooleanWidget extends QuestionWidget {

    public BooleanWidget(Question question, Value value) {
        super(question);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(Boolean.valueOf(value.toString()));
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        this.add(checkBox, 1);
    }
}
