package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class IntegerSliderWidget extends Widget {

    private final JSlider slider;

    public IntegerSliderWidget(QuestionModel questionModel) {
        super(questionModel);

        JSlider slider = new JSlider(JSlider.HORIZONTAL);

        if (questionModel.getAssignedExpression() != null) {
            slider.setEnabled(false);
        }

        slider.addChangeListener(e -> questionModel.changeValue(BigInteger.valueOf(slider.getValue())));
        slider.setValue(0);
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        this.slider = slider;
    }

    @Override
    public void updateValue() {
        this.slider.setValue((Integer) this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public JComponent getComponent() {
        return this.slider;
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.slider.getPreferredSize();
        size.width = width;
        this.slider.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.slider.setFont(new Font(font, Font.PLAIN, this.slider.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.slider.setFont(new Font(this.slider.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.slider.setBackground(Color.decode(color));
    }

    public void setMin(Integer min) {
        this.slider.setMinimum(min);
        this.slider.setValue(min);
    }

    public void setMax(Integer max) {
        this.slider.setMaximum(max);
    }

    public void setStep(Integer step) {
        this.slider.setMajorTickSpacing(step);
    }
}
