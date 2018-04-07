package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class IntegerSpinnerWidget extends Widget {

    private final JSpinner spinner;
    private SpinnerNumberModel spinnerModel;

    public IntegerSpinnerWidget(QuestionModel questionModel) {
        super(questionModel);

        this.spinnerModel = new SpinnerNumberModel(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1
        );

        JSpinner spinner = new JSpinner(this.spinnerModel);

        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner);
        editor.getFormat().setGroupingUsed(false);
        spinner.setEditor(editor);

        if (questionModel.getAssignedExpression() != null) {
            spinner.setEnabled(false);
        }

        spinner.addChangeListener(e -> questionModel.changeValue(new BigInteger(spinner.getValue().toString())));

        this.spinner = spinner;
    }

    @Override
    public void updateValue() {
        this.spinner.setValue(this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public JComponent getComponent() {
        return this.spinner;
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.spinner.getPreferredSize();
        size.width = width;
        this.spinner.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.spinner.setFont(new Font(font, Font.PLAIN, this.spinner.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.spinner.setFont(new Font(this.spinner.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.spinner.setBackground(Color.decode(color));
    }

    public void setMin(Integer min) {
        System.out.println("Setting min: " + min);
        this.spinnerModel.setMinimum(min);
        this.spinnerModel.setValue(min);

    }

    public void setMax(Integer max) {
        System.out.println("Setting max: " + max);
        this.spinnerModel.setMaximum(max);

    }

    public void setStep(Integer step) {
        System.out.println("Setting step: " + step);
        this.spinnerModel.setStepSize(step);

    }
}
