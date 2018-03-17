package org.uva.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.gui.QuestionChangeListener;

import javax.swing.*;

public class StringWidget extends QuestionWidget {

    private JTextField textField = new JTextField();

    public StringWidget(Question question, Value value, boolean readOnly) {
        super(question);

        textField.setText(value.getValue().toString());
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEnabled(readOnly);

        this.add(textField, 1);
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        textField.addActionListener(e -> questionChangeListener.onQuestionChanged(question, new StringValue(textField.getText())));
    }
}
