package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.QuestionChangeListener;

import javax.swing.*;

public class StringWidget extends QuestionWidget {

    private JTextField textField = new JTextField();

    public StringWidget(Question question, Value value, boolean readOnly, QuestionChangeListener questionChangeListener) {
        super(question);

        textField.setText(value.getValue().toString());
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEnabled(readOnly);

        textField.addActionListener(e -> questionChangeListener.onQuestionChanged(question.getName(), value));

        this.add(textField, 1);
    }
}
