package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SpinboxWidget extends QuestionWidget{

    private JSpinner spinner;

    public SpinboxWidget(Question question, Value value, boolean readOnly, Style style) {
        super(question);

        spinner = new JSpinner();
        spinner.setValue(value.getValue());
        spinner.setEnabled(readOnly);

        this.add(spinner, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }


    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        spinner.addChangeListener(e -> questionChangeListener.onQuestionChanged(question, new IntegerValue((int)spinner.getValue())));
    }
}
