package org.uva.ql.gui.widgets;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;

import javax.swing.*;

public class StringWidget extends QuestionWidget {

    public StringWidget(Question question, Value value) {
        super(question);

        JTextField textField = new JTextField(value.getValue().toString());
        textField.setHorizontalAlignment(JTextField.CENTER);
        this.add(textField, 1);
    }
}
