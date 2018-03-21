package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;


public class IntegerWidget extends QuestionWidget {

    private JTextField textField;

    public IntegerWidget(Question question, Value value, boolean readOnly, Style style) {
        super(question);

        textField = new JTextField();
        textField.setText(value.getValue().toString());
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEnabled(readOnly);
        this.add(textField, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        textField.addActionListener(e -> questionChangeListener.onQuestionChanged(question, new IntegerValue(Integer.parseInt(textField.getText()))));
    }
}
