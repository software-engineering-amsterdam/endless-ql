package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.QuestionChangeListener;

import javax.swing.*;

public class StringWidget extends QuestionWidget {

    public StringWidget(Question question, Value value, QuestionChangeListener questionChangeListener) {
        super(question);

        JTextField textField = new JTextField(value.getValue().toString());
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.addActionListener(e -> questionChangeListener.onQuestionChanged(question.getName(), value));
        this.add(textField, 1);
    }
}
