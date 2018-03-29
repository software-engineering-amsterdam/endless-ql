package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;

public class CheckboxWidget extends QuestionWidget {

    private JCheckBox checkBox;

    public CheckboxWidget(Question question, Value value, boolean readOnly, Style style, String label) {
        super(question);

        this.checkBox = new JCheckBox();
        this.checkBox.setSelected(Boolean.valueOf(value.toString()));
        this.checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        this.checkBox.setEnabled(readOnly);
        this.checkBox.setText(label);
        this.checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
        this.add(checkBox, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        checkBox.addItemListener(e -> questionChangeListener.onQuestionChanged(question, new BooleanValue(checkBox.isSelected())));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        checkBox.setBackground(color);
    }
}
