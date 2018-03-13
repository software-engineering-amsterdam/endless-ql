package gui.view.widgets;

import ast.model.expressions.Expression;
import gui.model.FormQuestion;
import gui.view.FormPanel;
import gui.view.Widget;

import javax.swing.*;

public class IntegerSpinnerWidget extends Widget {

    private JSpinner spinner;

    public IntegerSpinnerWidget(FormQuestion formQuestion) {
        super(formQuestion);

        SpinnerModel spinnerModel = new SpinnerNumberModel(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1
        );

        JSpinner spinner = new JSpinner(spinnerModel);

        if (formQuestion.getAssignedExpression() != null) {
            spinner.setEnabled(false);
        }

        spinner.addChangeListener(e -> {
            System.out.println("Spinner value changed to: " + spinner.getValue());
            formQuestion.getValue().setIntegerValue((Integer) spinner.getValue());
        });

        this.spinner = spinner;
    }

    @Override
    public Expression.DataType getSupportedDataType() {
        return Expression.DataType.INTEGER;
    }

    @Override
    public JComponent getComponent() {
        return this.spinner;
    }
}
