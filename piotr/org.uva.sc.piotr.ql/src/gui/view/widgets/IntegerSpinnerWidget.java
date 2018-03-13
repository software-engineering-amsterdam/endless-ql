package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.Widget;

import javax.swing.*;

public class IntegerSpinnerWidget extends Widget {

    private JSpinner spinner;

    public IntegerSpinnerWidget(FormQuestionHolder formQuestionHolder) {
        super(formQuestionHolder);

        SpinnerModel spinnerModel = new SpinnerNumberModel(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1
        );

        JSpinner spinner = new JSpinner(spinnerModel);

        if (formQuestionHolder.getAssignedExpression() != null) {
            spinner.setEnabled(false);
        }

        spinner.addChangeListener(e -> {
            formQuestionHolder.changeValue((Integer) spinner.getValue());
        });

        this.spinner = spinner;
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.INTEGER;
    }

    @Override
    public void updateValue() {
        this.spinner.setValue(this.getFormQuestionHolder().getValueHolder().getIntegerValue());
    }

    @Override
    public JComponent getComponent() {
        return this.spinner;
    }
}
