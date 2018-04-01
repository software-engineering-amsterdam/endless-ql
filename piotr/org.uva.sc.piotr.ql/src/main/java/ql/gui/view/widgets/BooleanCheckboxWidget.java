package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class BooleanCheckboxWidget extends Widget {

    private final JCheckBox checkbox;

    public BooleanCheckboxWidget(QuestionModel questionModel) {
        super(questionModel);
        JCheckBox checkbox = new JCheckBox();

        if (questionModel.getAssignedExpression() != null) {
            checkbox.setEnabled(false);
        }

        checkbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                questionModel.changeValue(true);

            } else {
                questionModel.changeValue(false);
            }
        });

        this.checkbox = checkbox;
    }

    @Override
    public JComponent getComponent() {
        return this.checkbox;
    }

    @Override
    public void updateValue() {
        this.checkbox.setSelected((Boolean) this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.checkbox.getPreferredSize();
        size.width = width;
        this.checkbox.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
    }

    @Override
    public void setFontSize(Integer fontSize) {
    }

    @Override
    public void setColor(String color) {
        this.checkbox.setBackground(Color.decode(color));
    }
}
