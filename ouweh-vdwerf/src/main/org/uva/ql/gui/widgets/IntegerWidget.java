package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.gui.QuestionChangeListener;

import javax.swing.*;
import java.awt.*;

public class IntegerWidget extends QuestionWidget {

    public IntegerWidget(Question question, Value value, QuestionChangeListener questionChangeListener) {
        super(question);

        JTextField textField = new JTextField(value.getValue().toString());
        textField.setHorizontalAlignment(JTextField.CENTER);

        textField.addActionListener(e -> questionChangeListener.onQuestionChanged(question.getName(), new StringValue(textField.getText())));
        this.add(textField, 1);
    }
}
